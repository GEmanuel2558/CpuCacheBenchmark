# CpuCacheBenchmark

The purpose of this project is to test write on the CPU cache level 1, 2 and 3 using volatile variabiles with and without padding. The output result of my test is:

	Padded # threads 1 - T = 551ms
	UnPadded # threads 1 - T = 2836ms
	Total time # threads 1 - T = 3414ms

	Padded # threads 2 - T = 578ms
	UnPadded # threads 2 - T = 2741ms
	Total time # threads 2 - T = 3319ms

	Padded # threads 3 - T = 527ms
	UnPadded # threads 3 - T = 3009ms
	Total time # threads 3 - T = 3538ms

	Padded # threads 4 - T = 568ms
	UnPadded # threads 4 - T = 2841ms
	Total time # threads 4 - T = 3409ms


