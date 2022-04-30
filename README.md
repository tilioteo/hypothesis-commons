# hypothesis-commons
Common interfaces and utilities for hypothesis plugins.

hypothesis-commons is a UI component add-on for Vaadin 7.

### Debugging server-side

If you have not already compiled the widgetset, do it now by running vaadin:install Maven target for commons-root project.

If you have a JRebel license, it makes on the fly code changes faster. Just add JRebel nature to your commons-demo project by clicking project with right mouse button and choosing JRebel > Add JRebel Nature

To debug project and make code modifications on the fly in the server-side, right-click the commons-demo project and choose Debug As > Debug on Server. Navigate to http://localhost:8080/commons-demo/ to see the application.

### Debugging client-side

Debugging client side code in the commons-demo project:
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application or by adding ?superdevmode to the URL
  - You can access Java-sources and set breakpoints inside Chrome if you enable source maps from inspector settings.
 
## Release notes

### Version 1.6.0-SNAPSHOT

## License

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.
