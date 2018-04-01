CC=/opt/SUNWspro/bin/cc -DSOLARIS
LIB=-mt -lmqm -lmqmcs -lmqmzse -lsocket -lnsl -ldl
all: mqput2 mqputs mqtimes mqtimes2 mqtimes3 mqcapture mqcapone mqtest mqreply mqlatency mqcapsub

comsubs.o: comsubs.c comsubs.h int64defs.h
	$(CC) ${INC} -c comsubs.c

qsubs.o: qsubs.c qsubs.h int64defs.h
	$(CC) ${INC} -c qsubs.c 

rfhsubs.o: rfhsubs.c rfhsubs.h int64defs.h
	${CC} ${INC} -c rfhsubs.c 

parmline.o: parmline.c parmline.h int64defs.h
	${CC} ${INC} -c parmline.c 

putparms.o: putparms.c putparms.h int64defs.h
	${CC} ${INC} -c putparms.c 

timesubs.o: timesubs.c timesubs.h int64defs.h
	${CC} ${INC} -c timesubs.c 

mqput2: mqput2.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	$(CC) ${INC} -o mqput2 mqput2.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB} 

mqputs.o: mqput2.c int64defs.h
	${CC} ${INC} -c mqput2.c -o mqputs.o -DNOTUNE

mqputs: mqputs.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	${CC} ${INC} -o mqputs mqputs.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB}

mqlatency: mqlatency.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	${CC} ${INC} -o mqlatency mqlatency.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB}

mqtest: mqtest.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o
	$(CC) ${INC} -o mqtest mqtest.o qsubs.o comsubs.o rfhsubs.o parmline.o putparms.o timesubs.o ${LIB} 

mqreply: mqreply.o comsubs.o parmline.o putparms.o qsubs.o rfhsubs.o
	$(CC) ${INC} -o mqreply mqreply.o comsubs.o parmline.o putparms.o qsubs.o rfhsubs.o ${LIB}

mqtimes: mqtimes.o
	$(CC) -o mqtimes mqtimes.o ${LIB}

mqtimes2: mqtimes2.o comsubs.o parmline.o putparms.o timesubs.o qsubs.o rfhsubs.o
	$(CC) -o mqtimes2 mqtimes2.o comsubs.o parmline.o putparms.o qsubs.o timesubs.o rfhsubs.o ${LIB}

mqtimes3: mqtimes3.o comsubs.o parmline.o putparms.o qsubs.o timesubs.o rfhsubs.o
	${CC} -o mqtimes3 mqtimes3.o comsubs.o parmline.o putparms.o qsubs.o timesubs.o rfhsubs.o ${LIB}

mqcapture: mqcapture.o comsubs.o parmline.o putparms.o qsubs.o timesubs.o rfhsubs.o
	$(CC) -o mqcapture mqcapture.o comsubs.o parmline.o putparms.o qsubs.o timesubs.o rfhsubs.o ${LIB}

mqcapone: mqcapone.o comsubs.o parmline.o putparms.o qsubs.o timesubs.o rfhsubs.o
	$(CC) -o mqcapone mqcapone.o comsubs.o parmline.o putparms.o qsubs.o timesubs.o rfhsubs.o ${LIB}

mqcapsub: mqcapsub.o comsubs.o parmline.o putparms.o qsubs.o timesubs.o rfhsubs.o
	$(CC) -o mqcapsub mqcapsub.o comsubs.o parmline.o putparms.o qsubs.o timesubs.o rfhsubs.o ${LIB}

.c.o:
	$(CC) -c $<

clean:
	rm *.o

