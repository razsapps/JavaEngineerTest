package com.ras.java.test.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.fail;

/**
 * This class is designed to execute efficiency tests in a standard way across the system.
 *
 * There is no guarantee this will always fail/pass correctly, but the idea is to at least attempt to help a user
 * to see there could potentially be some efficiency improvements that can be made.
 */
public class EfficiencyTestExecutor {
    //This is the deviation we are allowing in our efficiency tests between the expected time and actual time
    private static final double DEVIATION = 0.1;
    private static final long MIN_EXPECTED_TIME = 10;

    private Class m_questionClass;
    private Class m_testClass;
    private String m_methodName;
    private Class[] m_parameterTypes;
    private Object[] m_questionInput;
    private Object[] m_testInput;

    /**
     * @param questionClass The class where the developer will attempt to implement their answer (i.e. ArrayQuestions)
     */
    public EfficiencyTestExecutor setQuestionClass(Class questionClass) {
        m_questionClass = questionClass;
        return this;
    }

    /**
     * @param testClass The test class where the developer's work is tested (i.e. ArrayQuestionsIsPalindromeTest)
     */
    public EfficiencyTestExecutor setTestClass(Class testClass) {
        m_testClass = testClass;
        return this;
    }

    /**
     * @param methodName The method name of the method being tested in the Question class (and test class)
     */
    public EfficiencyTestExecutor setMethodName(String methodName) {
        m_methodName = methodName;
        return this;
    }

    /**
     * @param parameterTypes The type of parameters the method being tested takes
     */
    public EfficiencyTestExecutor setParameterTypes(Class... parameterTypes) {
        m_parameterTypes = parameterTypes;
        return this;
    }

    /**
     * If the inputs are not manipulated then you can use this method.
     * @param inputs The values the method can be invoked with
     */
    public EfficiencyTestExecutor setInputs(Object... inputs) {
        setQuestionInput(inputs);
        setTestInput(inputs);
        return this;
    }

    /**
     * The input for executing the Question class's implementation
     * @param inputs The values the method can be invoked with
     */
    public EfficiencyTestExecutor setQuestionInput(Object... inputs) {
        m_questionInput = inputs;
        return this;
    }

    /**
     * The input for executing the test class's implementation
     * @param inputs The values the method can be invoked with
     */
    public EfficiencyTestExecutor setTestInput(Object... inputs) {
        m_testInput = inputs;
        return this;
    }

    private boolean isAnyNull(Object... objs) {
        for (Object obj: objs) {
            if (obj == null)
                return true;
        }
        return false;
    }

    private long getTiming(Class clazz, Object... inputs) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        long start = System.currentTimeMillis();
        Method method = clazz.getMethod(m_methodName, m_parameterTypes);
        //This is a special case in case the input is a single array of objects.  This would cause each element
        //in the array to be handled as a a specific parameter. By wrapping it in another array we ensure this
        //behaves correctly
        if (m_parameterTypes.length == 1)
            inputs = new Object[]{inputs};
        method.invoke(null, inputs);
        return System.currentTimeMillis() - start;
    }

    /**
     * Tests if the developer's question implementation is just as fast or faster than the test's answer implementation
     */
    public void testEfficient() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (isAnyNull(m_questionClass, m_testClass, m_methodName, m_parameterTypes, m_questionInput, m_testInput))
            throw new IllegalArgumentException("All values must be provided to execute this method.");

        long expectedTime = getTiming(m_testClass, m_testInput);
        //Add deviation
        expectedTime = expectedTime + (long)(expectedTime * DEVIATION);
        //Dealing with very small numbers (or 0) can be problematic. Let's use a minimum
        if (expectedTime < MIN_EXPECTED_TIME)
            expectedTime = MIN_EXPECTED_TIME;

        long actualTime = getTiming(m_questionClass, m_questionInput);

        System.out.println(m_testClass.getName() + "." + m_methodName + "{" + expectedTime + "," + actualTime + "}");
        boolean efficient = actualTime <= expectedTime;
        if (!efficient)
            fail("This may not be the most efficient way to implement the solution");
    }
}
