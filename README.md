# Netty Discard Server Example

A minimal maven project using [Netty][1].
This [sample code][2] is from taken from the netty distribution.

[1]: http://www.jboss.org/netty
[2]: http://docs.jboss.org/netty/3.2/xref/org/jboss/netty/example/discard/package-summary.html

## Notes

This maven project also includes ```.project``` and ```.classpath``` files 
and thus is a valid eclipse project. To be able to build it using eclipse,
you need to pull the dependencies though. You can trigger that by

    mvn package
    
and maybe some way easier; not into maven so deep (yet).

## Archetypes

To create an archetype from this project you can use:

    mvn archetype:create-from-project

To install the archetype in your local maven repo use:

    cd target/generated-sources/archetype/
    mvn install

To create a project from your archetype use:

    mvn archetype:generate -DarchetypeCatalog=local

and select yours.
