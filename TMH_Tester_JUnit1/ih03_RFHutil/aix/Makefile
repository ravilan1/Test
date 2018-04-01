CC=/usr/vac/bin/cc
LIB=-lmqm

all: mqput2 mqputs  mqtimes mqtimes2 mqtimes3 mqcapture mqcapone mqtest mqreply mqlatency mqcapsub

comsubs.o: comsubs.c comsubs.h
	$(CC) ${INC} -c comsubs.c

timesubs.o: timesubs.c timesubs.h
	$(CC) ${INC} -c timesubs.c

qsubs.o: qsubs.c qsubs.h
	$(CC) ${INC} -c qsubs.c 

rfhsubs.o: rfhsubs.c rfhsubs.h
	${CC} ${INC} -c rfhsubs.c 

parmline.o: parmline.c parmline.h
	${CC} ${INC} -c parmline.c 

putparms.o: putparms.c putparms.h
	${CC} ${INC} -c putparms.c 

mqput2: mqput2.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	$(CC) ${INC} -o mqput2 mqput2.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB} 

mqputs.o: mqput2.c
	${CC} ${INC} -c mqput2.c -o mqputs.o -DNOTUNE

mqputs: mqputs.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	${CC} ${INC} -o mqputs mqputs.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB}

mqtest: mqtest.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	$(CC) ${INC} -o mqtest mqtest.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB} 

mqreply: mqreply.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o 
	$(CC) ${INC} -o mqreply mqreply.o comsubs.o qsubs.o ${LIB}

mqlatency: mqlatency.o comsubs.o qsubs.o parmline.o putparms.o rfhsubs.o timesubs.o
	$(CC) $(INC) -o mqlatency mqlatency.o qsubs.o comsubs.o parmline.o putparms.o rfhsubs.o timesubs.o ${LIB}

mqtimes: mqtimes.o
	$(CC) ${INC} -o mqtimes mqtimes.o ${LIB}

mqtimes2: mqtimes2.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	$(CC) ${INC} -o mqtimes2 mqtimes2.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB}

mqtimes3: mqtimes3.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	$(CC) ${INC} -o mqtimes3 mqtimes2.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB}

mqcapture: mqcapture.o comsubs.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	$(CC) ${INC} -o mqcapture mqcapture.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB}

mqcapone:mqcapone.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	$(CC) ${INC} -o mqcapone mqcapone.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB}

mqcapsub:mqcapsub.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	$(CC) ${INC} -o mqcapsub mqcapsub.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB}

.c.o:
	$(CC) -c $<

clean:
	rm *.o

