PROG		= benchmark
CXX		= g++
CXXFLAGS	= -Wall -Wextra -Wpedantic -O3 -flto -std=c++11
SIZES		?= 10 100 1000 10000 100000 1000000 10000000 100000000
REQUESTS	?= 1000000
MEASUREMENTS	?= 100
HITPROBABILITY	?= 0.5

.PHONY: all run clean

all: $(PROG)

run: $(PROG)
	@$(foreach i, $(SIZES), /bin/echo -n "$i: "; ./benchmark $i $(REQUESTS) $(MEASUREMENTS) $(HITPROBABILITY);)

clean:
	rm -rf *.o *~ $(PROG)
