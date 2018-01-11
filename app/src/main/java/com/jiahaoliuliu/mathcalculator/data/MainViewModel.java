package com.jiahaoliuliu.mathcalculator.data;

import android.content.Context;
import android.content.Intent;

import com.jiahaoliuliu.mathcalculator.config.ConfigurationModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainViewModel {
    public static final String INTENT_ACTION_EXERCISE_TIMER = "INTENT_ACTION_EXERCISE_TIMER";
    public static final String INTENT_EXTRAS_EXERCISE_TIMER = "INTENT_EXTRAS_EXERCISE_TIMER";

    // Configs
    private static final boolean ALLOW_NEGATIVE_NUMBER_ON_EXTRACTION = false;

    private List<MathOperationModel> mathOperationModelsCollection;
    private Iterator<MathOperationModel> collectionIterator;

    // Singleton
    private static MainViewModel mainViewModel;

    // Timer
    private Timer timer;
    private TimerTask timerTask;
    private int exerciseTimeInSeconds;

    // Configuration
    private ConfigurationModel currentConfigurationModel;

    // Persistence
    private PersistentManager persistentManager;

    private MainViewModel() {
        // init the variable
        persistentManager = PersistentManager.getInstance();
        mathOperationModelsCollection = new ArrayList<>();
        currentConfigurationModel = getCurrentConfigurationModel();
    }

    public static MainViewModel getInstance() {
        if (mainViewModel == null) {
            mainViewModel = new MainViewModel();
        }

        return mainViewModel;
    }

    public void generateNewMathOperationModelsList() {
        // Restart the content of the list
        mathOperationModelsCollection.clear();
        collectionIterator = null;

        // Retrieve the current configuration
        int numberOfExercises = currentConfigurationModel.getNumberOfExercises();
        for (int i = 0; i < numberOfExercises; i++) {
            MathOperationModel mathOperationModel = generateMathOperationModel();

            // Avoid duplications
            while(mathOperationModelsCollection.contains(mathOperationModel)) {
                mathOperationModel = generateMathOperationModel();
            }

            // if it is the last number
            if (i == numberOfExercises - 1) {
                mathOperationModel.setLastOperation(true);
            }
            mathOperationModelsCollection.add(mathOperationModel);
        }

        collectionIterator = mathOperationModelsCollection.iterator();
    }

    /**
     * Get the next math operation to be done
     *
     * @return
     *      The next math operation
     */
    public MathOperationModel getNextMathOperationModel() {
        if (collectionIterator != null && collectionIterator.hasNext()) {
            return collectionIterator.next();
        }

        return null;
    }

    public int getExerciseOrdinalNumber(MathOperationModel mathOperationModel) {
        return mathOperationModelsCollection.indexOf(mathOperationModel);
    }

    public int getTotalNumberOfExercises() {
        return mathOperationModelsCollection.size();
    }

    /**
     * Get the list of results
     * @return
     *      The list of results
     */
    public List<MathOperationModel> getResultsList() {
        return mathOperationModelsCollection;
    }

    public GeneralResultModel getGeneralResult() {
        int numberOfCorrectResults = 0;
        for (MathOperationModel mathOperationModel: mathOperationModelsCollection) {
            if (mathOperationModel.getGivenResult() == mathOperationModel.getCorrectResult()) {
                numberOfCorrectResults++;
            }
        }

        return new GeneralResultModel(numberOfCorrectResults,
                mathOperationModelsCollection.size() - numberOfCorrectResults);
    }

    /**
     * Start the timer
     */
    public void startTimer(final Context context) {
        // Restart the time
        exerciseTimeInSeconds = 0;
        // Restart the timer
        timer = new Timer();
        // Restart the timer task
        timerTask = new TimerTask() {
            @Override
            public void run() {
                exerciseTimeInSeconds++;
                // Broadcast the time
                Intent timerIntent = new Intent(INTENT_ACTION_EXERCISE_TIMER);
                timerIntent.putExtra(INTENT_EXTRAS_EXERCISE_TIMER, exerciseTimeInSeconds);
                context.sendBroadcast(timerIntent);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public int getExerciseTime() {
        return exerciseTimeInSeconds;
    }

    public void stopTimer() {
        // Cancel the timer
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        // Cancel the timer task
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    private MathOperationModel generateMathOperationModel() {
        Random random = new Random();
        MathOperationModel.Operation mathOperation = generateOperation(random);
        int firstNumber = 0;
        int secondNumber = 0;
        switch (mathOperation) {
            case ADDITION:
                firstNumber = random.nextInt(currentConfigurationModel.getMaximumAdditionNumber());
                secondNumber = random.nextInt(currentConfigurationModel.getMaximumAdditionNumber());
                // Not exceed the maximum number
                if (mathOperation.operate(firstNumber, secondNumber) >
                        currentConfigurationModel.getMaximumAdditionNumber()) {
                    // Generate a new number from the first number
                    int result = random.nextInt(
                            currentConfigurationModel.getMaximumAdditionNumber()
                                    - firstNumber + 1 ) + firstNumber;
                    secondNumber = result - firstNumber;
                }
                break;
            case EXTRACTION:
                firstNumber = random.nextInt(currentConfigurationModel.getMaximumExtractionNumber());
                secondNumber = random.nextInt(currentConfigurationModel.getMaximumExtractionNumber());
                // Negative number on extraction
                if (!ALLOW_NEGATIVE_NUMBER_ON_EXTRACTION) {
                    // if the first number is zero, then set the second number as zero
                    if (firstNumber == 0) {
                        secondNumber = 0;
                    } else {
                        // For now the negative number on extraction is not allowed. The new number i
                        while (secondNumber > firstNumber) {
                            secondNumber = random.nextInt(firstNumber);
                        }
                    }
                }
                break;
            case MULTIPLICATION:
                firstNumber =
                        random.nextInt(currentConfigurationModel.getMaximumMultiplicationNumber());
                secondNumber =
                        random.nextInt(currentConfigurationModel.getMaximumMultiplicationNumber());
                // There is not other restriction on multiplication
                break;
        }


        int correctResult = mathOperation.operate(firstNumber, secondNumber);

        return new MathOperationModel(firstNumber, mathOperation, secondNumber,
                correctResult, false);
    }

    /**
     * Generate a random operation that is allowed by the current configuration
     * @param random
     *      The random generator
     * @return
     *      A random operation that is allowed by the current configuration
     */
    private MathOperationModel.Operation generateOperation(Random random) {
        MathOperationModel.Operation operation;
        do {
            operation = MathOperationModel.Operation.retrieveOperation(
                    random.nextInt(MathOperationModel.Operation.values().length));
        } while (!isOperationAllowed(operation));

        return operation;
    }

    /**
     * Check if a certain math operation is allowed by the current configuration
     * @param operation
     * @return
     */
    private boolean isOperationAllowed(MathOperationModel.Operation operation) {
        switch (operation) {
            case ADDITION:
                return currentConfigurationModel.isAdditionAllowed();
            case EXTRACTION:
                return currentConfigurationModel.isExtractionAllowed();
            case MULTIPLICATION:
                return currentConfigurationModel.isMultiplicationAllowed();
            default:
                return true;
        }
    }


    // Get the current configuration model.
    public ConfigurationModel getCurrentConfigurationModel() {
        if (currentConfigurationModel == null) {
            if (!persistentManager.contains(PersistentManager.StringKey.CONFIGURATION_MODEL)) {
                currentConfigurationModel = new ConfigurationModel();
            } else {
                currentConfigurationModel =
                        new ConfigurationModel(
                                persistentManager.get(
                                        PersistentManager.StringKey.CONFIGURATION_MODEL));
            }
        }

        return currentConfigurationModel;
    }

    public void setCurrentConfigurationModel(ConfigurationModel configurationModel) {
        this.currentConfigurationModel = configurationModel;

        persistentManager.set(PersistentManager.StringKey.CONFIGURATION_MODEL,
                this.currentConfigurationModel.toJson());
    }
}
