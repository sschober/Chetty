# Chetty

A simple chat server using [Netty][1].

[1]: http://www.jboss.org/netty

## Building and Running

This is a Maven project, so

    mvn package

should do the job to build an package the sources.

There are also Eclipse metadata files, so you can import the project
to your existing workspace and simply run it (You might actually
need to install [m2eclipse][2] and configure the classpath variable
```M2RPO```).

[2]: http://m2eclipse.sonatype.org/

## Usage

Once the server is running, you use telnet to connect to it:

    telnet localhost 8080

Connected, you have to register first:

    /r someone

Then you can list all registered users:

    /ul

And finally establish a chat session with one of them:

    /q anotherone

Then, everything you type gets sent to your chat partner(s) when you
hit enter. When you are tired of your peer you can end the session
by typing:

    /l


## Architecture

When designing the server i tried to leverage the handler pipline as
much as i could. So, instead of a single (business logic) handler
parsing strings and one giant switch statement (or even worse: an
if-else if cascade), each command is implemented as a separate
handler. Each handler gets to look at a string the user sends an
decides if it should act about it. If the handler decides it`s
responsible it handles the request and the processing stops.
Otherwise the next handler get`s a go.

