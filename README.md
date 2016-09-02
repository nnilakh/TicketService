# Ticket Service: Coding Assessment

### 1. Assumptions:
1.	Ticket service is a single threaded application
2.	SeatScore is dependent on the seat’s proximity to the stage.
3.	One can reserve a seat only if the seat is currently held.
### 2. Build Instructions:

From the command line terminal: mvn test	

###  3.	Dependencies:
1.	Java 8
2.	JUnit
3.	Maven3.2

### 4.	Screenshot’s of Test:

![Alt text](/TestScreenShot.png?raw=true " ")

![Alt text](/BuildScreenShot.png?raw=true " ")


### 6. Class Diagram:

![Alt text](/TicketServiceClassDiagram.jpg?raw=true "Class Diagram")


### 7. Possible Enhancements (Can be implemented with some more time):

1.	The In Memory repository can be replaced by a database.
2.	The service can be modified to run in a multi-threaded environment 
3.	The code can be structured into MVC pattern
