# Java Thread Counter: Thread Safety and Race Conditions
## Introduction

This experiment investigates the behavior of shared and instance variables in a multithreaded Java program. It compares thread-safe and non-thread-safe counter implementations to understand race conditions and the importance of synchronization when multiple threads access shared mutable data.

The program uses Java's `Runnable` interface to create multiple threads. Each thread performs a specified number of counter increments, and the resulting values are compared with the expected total.

## Objectives

The main objectives of this experiment are:

1. To understand multithreading in Java.
2. To implement concurrent tasks using the `Runnable` interface.
3. To compare thread-safe and non-thread-safe counter operations.
4. To observe race conditions caused by concurrent access to shared variables.
5. To examine the behavior of instance variables in separate thread objects.
6. To calculate the difference between the expected and actual counter values.

##  Implementation Details

The program, `Azhar_Thread.java`, implements three counters:

- **Thread-safe counter:** Uses `AtomicLong` to perform atomic increment operations.
- **Non-thread-safe counter:** Uses a shared `long` variable with the `++` operator, which is not atomic.
- **Instance counter:** Each thread object maintains its own counter, independent of the other thread objects.

The program accepts three command-line arguments:

`java Azhar_Thread <numberOfThreads> <incrementsPerThread> <threadSafe>`

| Argument | Description |
|---|---|
| `numberOfThreads` | Number of threads to create |
| `incrementsPerThread` | Number of increments performed by each thread |
| `threadSafe` | `true` selects the atomic counter; `false` selects the non-atomic counter |

The expected total is calculated as:

**Expected total = Number of threads × Increments per thread**

After all threads finish execution, the program displays the expected count, actual counter values, absolute difference, and percentage difference.

## Requirements

- Java Development Kit (JDK)
- Command Prompt or terminal
- A text editor or IDE, such as Visual Studio Code or IntelliJ IDEA

## How to Compile and Run

Open a terminal in the directory containing `Azhar_Thread.java`.

### Step 1: Compile the program

```bash
javac Azhar_Thread.java
```

### Step 2: Run a thread-safe test

```bash
java Azhar_Thread 2 10000 true
```

### Step 3: Run a non-thread-safe test

```bash
java Azhar_Thread 2 10000 false
```

The final argument selects the counter implementation. Use `true` for the thread-safe counter and `false` for the non-thread-safe counter.

## Experimental Test Cases

The following test cases are used to evaluate the program.

| Test Case | Number of Threads | Increments per Thread |
|---|---:|---:|
| TC1 | 1 | 1,000 |
| TC2 | 2 | 10,000 |
| TC3 | 5 | 10,000 |
| TC4 | 10 | 50,000 |
| TC5 | 20 | 50,000 |
| TC6 | 50 | 50,000 |
| TC7 | 100 | 50,000 |

For each test case, execute both modes:

- Thread-safe mode: `true`
- Non-thread-safe mode: `false`

Run each non-thread-safe test case five times and record the actual output for every run. Record the thread-safe result as well.

## Results and Observations

Record the observed output in `results_template.csv`. Include the expected count, actual count, absolute difference, and percentage difference for each execution.

The thread-safe counter is expected to produce the correct total because `AtomicLong` supports atomic increment operations. The non-thread-safe counter may produce a lower total because concurrent increments can interfere with one another. However, the observed result depends on the execution, and a race condition does not guarantee that every run will produce an incorrect value.

The instance counter is maintained separately by each thread object, so its total can be calculated by summing the individual instance counters after all threads have completed.

**Note:** All reported values must be taken from actual program executions.

## Conclusion

This experiment demonstrates the importance of thread safety when multiple threads modify shared data. It shows how atomic operations help maintain counter correctness and how non-atomic operations can introduce race conditions. It also illustrates the difference between shared variables and instance variables in a multithreaded Java application.

The experiment provides practical experience with Java threads, concurrent execution, atomic operations, and the verification of expected and actual results.

## Project Files

- `Azhar_Thread.java` — Java source code.
- `results_template.csv` — Template for recording experimental results.
- `handwritten_code/` — Location for the handwritten source-code scan.
- `outputs/` — Location for screenshots of actual program outputs.
- `Thread_Counter_Report_Template.pdf` — Report template to complete before submission.
