## Developer Notes 

### Expandability 
In order to extend Josch you need to have an extension that implements the respective 
interface methods. These can be found in `josch.layer.interfaces`, e.g. `josch.services.interfaces`.
Note that one should consider Abstract classes as interfaces, if the implementations share common
methods. The extensions should be added as Maven submodules within the implementations, e.g. 
`josch.services.comparison.ijssubset`. Each submodule is required to have a `module-info.java` in 
order to avoid transitive dependencies and needs to be registered in the parent `pom.xml`. Examples 
can be found in every leaf module. 

You then need to modify two more things: 
- The extension enum in order to show register the extension on the view. These are found in the
 `josch.model` module and are enums in order to avoid misspelling across layers. If you wanted to 
 add a containment tool for example, you had to configure the `josch.model.EContainmentTools.java`.
- The factory of the layer found in `josch.layer.factory`, e.g. `josch.services.factory`. 
An implementation needs to be registered at the factory by adding it to the respective switch 
statement. 

### Java Warnings 

The current project does have some warnings but note that these are intentional and do not require 
further work. They are being suppressed. 

#### Raw Types 

While Josch does attempt to parameterize all generic types, it is sometimes required to have raw 
types. Josch uses Raw types w.r.t. frameworks when these are sample implementations or discussed with 
the developers of the framework as the best solution to solve a certain task. Furthermore, Josch uses
 raw types to reduce duplicate code and to control the flow of the application. However, they are
  being avoided w.r.t. externally extended features, like containment, extraction  or database 
  systems. 
  
#### module-info requires

All requirements are vital to the functionality of Josch and its tests. Note that if a test requires
Maven modules the module has to be listed. However, your IDE might not recognize this as it scans the 
source but not the test folders necessarily.

### Errors and warnings w.r.t. MVC

In order to enforce a strict MVC pattern the views do not know their controllers. This is however
against the design of the JavaFX framework. By default, each view element knows its controller, 
elements and methods. 

Our design is that the controller does know the elements of the view and binds the actions 
accordingly. However, this is bloating the already relatively big controllers. For simplicity, we do
give the elements the action methods directly within the fxml while they don't know the 
implementations and thus indicate an error.  

Another warning w.r.t. not telling the view their controller is that the controller elements that 
are annotated with @FXML do not recognize the binding before runtime and thus the IDE indicates that 
they are redundant, while they are not. 







