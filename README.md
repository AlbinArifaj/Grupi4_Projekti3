# Aplikacion për komunikimin klient-server (TCP) ku të dhënat mes aplikacioneve shkëmbehen të kriptuara me DES-CBC, kurse çelësi sekret mbrohet me çelës publik.
University Of Prishtina - Faculty of Electrical and Computer Engineering/Department of Computer and Software Engineering

## Supervisor
*Second Project in Data Security Course supervised by Phd.Cand Mergim Hoti*

# Description
In this project, we had to make a TCP communication between Client and Server, but the data could be sent only one way, so the client could send data to the server, but the server could not send data to the client.

All the data being sent to the server needed to be encrypted using DES-CBC.

But we took it a few extra steps implementing some more functionality like:

1) Checking Port Availability
2) Allowing more than one user to connect
3) Adding a UI to display messages being sent

Now we are going to explain a little how we achieved each functionality.

##  TCP Communication 


We achieved TCP communication using Sockets in Java which allow communication between two processes. This is achieved by specifying the port that the users need to connect to and also the IP Address. If we are running both the CLIENT and the SERVER on the same machine, we do not need to specify it, but if we are using them on different machines, it is needed.

Examples of Socket Declaration in Java:

Server Side Socket:

```java
ServerSocket serverSocket = new ServerSocket(portNumber);
```
We specify only the port so the Server can be started.

Client Side Socket:

```java
Socket clientSocket = new Socket("localhost", portNumber);
```
Here we specify the port number but also specify the host in which the server is running so the user can connect. If they were in different places, then we need to specify the IP Address in which the server is running.

##  DES-CBC Encryption 
All the data that is being sent from the CLIENT to the SERVER need to be encrypted. The algorithm for encryption and decryption that we used is DES-CBC.

**Initialization Vector (IV):** A random IV, the size of a block (64 bits for DES), is generated to prevent identical plaintext from producing identical ciphertext.

**Padding:** If needed, the plaintext is padded to ensure its length is a multiple of the block size.

**Block Encryption:** The plaintext is divided into blocks, each matching the block size of DES (64 bits). If the last block is shorter, it's padded.

**CBC Encryption:** Each plaintext block is XORed with the previous ciphertext block before encryption, enhancing security.

**Initialization:** The IV is XORed with the first plaintext block.

**Encryption:** The resulting XOR output is encrypted using DES.

**Chaining:** The ciphertext block becomes input for the next block's XOR operation. Final Ciphertext: Once all blocks are encrypted and chained, the resulting ciphertext is generated.

**Decryption:** To decrypt, the process is reversed:

**Initialization:** The IV is XORed with the first ciphertext block.

**Decryption:** The resulting XOR output is decrypted using DES.

**Chaining:** The plaintext block becomes input for the next block's XOR operation.

**Padding Removal:** If padding was added, it's removed to recover the original plaintext.

##  Port Availability 
For port availability, we first checked if the port is in the range of the TCP/UDP; the range is from 1 to 65535.

After the first check, we then declare two different kinds of Sockets. These two Sockets are used to check if the port on my computer is in use.

ServerSocket is used to check for TCP ports.

DatagramSocket is used to check for UDP ports.

```java
ss = new ServerSocket(port);
ss.setReuseAddress(true);
ds = new DatagramSocket(port);
ds.setReuseAddress(true);
```
If everything checks out, then it allows the server to start on the specified port.

##  Allowing More Than One User to Connect 
TCP communications are single-threaded. What does this mean?

This means that when a server is started, only one user can connect, so the server allows only one user to connect and communicate with that user.

So what we did to bypass this was to make it multi-threaded. For each user, we create a new Thread to handle that user. This is implemented using the Java Thread API.

```java
Thread thread = new Thread();
```
So each time a new user is connected to the Server, a new Thread is created to handle that user, and all of these run concurrently.

This thread continues to handle communication until the server is closed.

##  Add UI to Display Messages Being Sent 
The application that we developed has a GUI. The GUI is implemented using JavaFX, which is a Java Library. All the GUI components are thanks to this class.

Explaining this library is a pretty big task. If you want to learn more about it, go to this [Link](https://docs.oracle.com/javafx/2/overview/jfxpub-overview.htm).

## Extra
The application is made using the MVC architecture, so everything is split: in the controller, we handle all the user input; in the FXML, we have all the design of the application; and in the other files, we handle the logic of the application.

## Contributors
1) Albin Arifaj
2) Albion Ahmeti
3) Albjon Tahirsylaj
4) Alfred Palokaj
