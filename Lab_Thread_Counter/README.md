# Lab Thread Counter

**Student:** Azhar  
**Problem:** SEPM-001 — Comparison of Thread Counting Using Static and Non-Static Variables

## Objective

Compare a shared static counter with the sum of per-object instance counters in Java multithreading. Run a thread-safe experiment using `AtomicLong` and an unsynchronized experiment using an ordinary `static long`.

## Files and folders

- `Azhar_Thread.java` — single-class Java source code.
- `handwritten_code/` — put clear photographs/scans of your handwritten source code here.
- `outputs/` — put genuine screenshots of program executions here. Suggested subfolders: `TC1/` through `TC7/`.
- `Thread_Counter_Report_Template.pdf` — report template; complete it with your own handwritten-code images and actual outputs.
- `results_template.csv` — blank recording sheet for actual results.

## Compile and run

Run these commands in a terminal opened in this directory:

```bash
javac Azhar_Thread.java
java Azhar_Thread 10 50000 true
java Azhar_Thread 10 50000 false
```

Arguments are: `number_of_threads increments_per_thread true_or_false`.

- `true`: thread-safe static counter using `AtomicLong`.
- `false`: ordinary unsynchronized static counter, which can lose updates.

## Test inputs

| Test | Threads | Increments per thread | Expected total |
|---|---:|---:|---:|
| TC1 | 1 | 1,000 | 1,000 |
| TC2 | 2 | 10,000 | 20,000 |
| TC3 | 5 | 10,000 | 50,000 |
| TC4 | 10 | 50,000 | 500,000 |
| TC5 | 20 | 50,000 | 1,000,000 |
| TC6 | 50 | 50,000 | 2,500,000 |
| TC7 | 100 | 50,000 | 5,000,000 |

For every test case, run both modes. Run the **unsynchronized mode at least five times per thread count**. Record every result and save screenshots of actual executions. Do not enter sample, guessed, or fabricated output.

## Recording results

Use `results_template.csv` to record each run. Keep the five unsafe runs as separate rows. Calculate:

- Expected count = threads × increments per thread
- Absolute difference = `|static count − non-static total|`
- Percentage difference = `(absolute difference / non-static total) × 100`

If both counts are zero, percentage difference is 0%. If the non-static total is zero but the static count is not zero, report it as undefined.

## Analysis questions

1. What is the difference between a static and a non-static variable in Java?
2. Why do all threads share the same static counter?
3. Why does each thread have its own non-static counter in this experiment?
4. Why is `join()` required before calculating final counts?
5. Why can the unsynchronized static count be lower than the expected count?
6. Does increasing the number of threads always increase the percentage difference? Explain using your observations.
7. Why might two runs with the same number of threads produce different results?
8. What changes when `AtomicLong` is used instead of an ordinary `long`?
9. How could the program be modified so all threads share one instance counter as well as the static counter?

## Observations and conclusion — complete after testing

**Thread-safe mode:** The static count should equal the expected count, and the non-static total should also equal it. Therefore, the difference should be zero.

**Unsynchronized mode:** Concurrent `unsafeCounter++` operations can overwrite one another's updates because incrementing a normal `long` is not an atomic read-modify-write operation. The observed static count may be lower than the expected count, but the exact loss is unpredictable. Some runs may still match the expected total.

A higher thread count does not guarantee a steadily increasing percentage difference. Results depend on scheduling, runtime conditions, and hardware. Compare the five unsafe runs for each test case and describe what your measurements actually show.

**Write your final conclusion here after filling in your measured results. Do not claim a trend that your data does not show.**

## Submission checklist

- [ ] Handwritten code scan/photo is readable and complete.
- [ ] Inputs and outputs for all seven test cases are recorded.
- [ ] Both modes were run for each test case.
- [ ] Five unsafe runs were recorded for each thread count (35 unsafe runs total).
- [ ] Result calculations and analysis questions are complete.
- [ ] Genuine output screenshots are included.
- [ ] PDF report is complete.
- [ ] This directory is committed and pushed to the GitHub repository.
- [ ] GitHub repository URL and `Lab_Thread_Counter` path are submitted through Canvas.
