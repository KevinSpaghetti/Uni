CC = gcc

weightedmedian: ./weightedmedian.c ../libs/medians.c ../libs/utility.c ../libs/order.c 
	${CC} weightedmedian.c -lm -o weightedmedian

profiler: profiler.c ./weightedmedian.c ../libs/random.c ../libs/medians.c ../libs/utility.c ../libs/order.c
	${CC} profiler.c -lm -o profiler

weightedmedian_select: ./weightedmedian.c ../libs/selection.c ../libs/medians.c ../libs/utility.c ../libs/order.c
	${CC} weightedmedian.c -D SELECTION -lm -o weightedmedian_select

profiler_select: profiler.c ./weightedmedian.c ../libs/random.c ../libs/selection.c ../libs/medians.c ../libs/utility.c ../libs/order.c
	${CC} profiler.c -D SELECTION -lm  -o profiler_select
