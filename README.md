# alami-test
Bank Data code to calculate average balance, assign free transfer quota and distribute bonus balance to several user's accounts using Java 8.

## Assumstion list
Due to limited time given and my current schedule seemly tight, I unable to verify few things. Hence I'll list assumtions that made during developing this program
1. Concurently process is only utilized to process the data. Not reading or writting it.
2. Process is executed step-by-step from question number 1 to 3.
3. Average balance is based on inital balanced and previous balanced. Not balanced after being added by process on question number 2b and 3.
3. Condition at process of question number 2a is based on initial balanced. Not balanced after being added by process on question number 2b and 3.
4. In order to process question number 3, we need to finish the process for question number 2. Since process on question number 3 could affect the condtion at question number 2a. Therefore, process of question number 3 will be executed after concurent process of question number 2 finish.
5. Thread number is thread id. Process number 1 to b2 will use 20 threads and process number 3 will use 3 threads.
