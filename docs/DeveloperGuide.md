---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `ay2122s1_cs2103t_w16_2.btbb.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.


### List feature

#### Overview

List operation can be executed for all entity types in the model,
ie. clients, orders, ingredients and recipes.

#### Mechanism

The list operation is facilitated by 

The following sequence diagram shows how the list operation works:

#### Usage Scenario



### Tab feature

#### Overview
Tabs are switched programmatically in 2 ways:
1. Tab command
1. Executing a command that affects the view of contents in a tab

#### Mechanism
The tab switch mechanism is facilitated by `CommandResult`.
This is because any programmatic tab switch will only occur after the execution of a command
and information about the switch has to be passed to `Ui` components.
After executing a command, if a tab switch should occur,
the tab to switch to is stored in a `CommandResult`.
This `CommandResult` is created in a `Ui` object through `MainWindow#executeCommand()`.
Hence, data about whether there is a tab switch and which tab to switch to
can be passed to `Ui` components and rendered accordingly.

The following sequence diagram shows how the tab operation works:

![TabSequenceDiagram](images/TabSequenceDiagram.png)

#### Usage Scenarios

The following activity diagram summarizes what happens when a user executes a command:

![TabActivityDiagram](images/TabActivityDiagram.png)

Example of a successful switch using the Tab command:
1. The user wishes to switch to the Home tab.
1. The user executes `tab 1` command to switch to the Home tab. The `commandText` is received by `MainWindow#executeCommand()` and the above mechanism occurs.
1. The app shows the Home tab.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Home chef
* Prepares meal orders at home
* Sells meals to clients through delivery
* Individual business owner
* Tracks orders, inventory and revenue
* Types quickly
* Prefers typing to mouse interactions
* Comfortable with CLI applications


**Value proposition**: The application allows home chefs who are individual business owners to track orders and revenue.
They can easily and quickly create new orders by copying bookmarked client and recipe details.
They can also use the app to track the status of their inventory.
Some business statistics are available to help home chefs make business plans.


### User stories

Priorities: High - (must have), Medium - (nice to have), Low -  (unlikely to have)

<table>
  <tr>
   <th style="text-align: center" colspan="3"><h3><strong>As a home chef</strong></h3></th>
  </tr>
  <tr>
    <th style="text-align: center"><h3><strong>Priority</strong></h3></th>
    <th style="text-align: center"><h3><strong>I can...</strong></h3></th>
    <th style="text-align: center"><h3><strong>So that...</strong></h3></th>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add an ingredient and quantity to the inventory</td>
    <td>I can keep track of my inventory</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Delete ingredients from the inventory</td>
    <td>My inventory is always up to date</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Edit ingredients in the inventory</td>
    <td>I can update my inventory with ingredients that I recently bought or used.</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Find ingredients by keywords</td>
    <td>I know the quantity of a specific ingredient</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View all ingredients in my inventory</td>
    <td>I can see the remaining quantity of all my ingredients</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Detect duplicate ingredients</td>
    <td>I do not clutter the application with duplicate ingredients</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add a client</td>
    <td>I can copy details from a client to easily fill up the details of an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Delete clients</td>
    <td>I can remove former clients</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Edit client details</td>
    <td>I can update their contact information</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Find clients by keywords</td>
    <td>I can find client information to fill up the details of an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View all clients</td>
    <td>I can view all my current clients</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Detect duplicate clients</td>
    <td>I do not clutter the application with duplicate clients</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add an order</td>
    <td>I can keep track of my orders</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add a quantity to an order</td>
    <td>I can create multiple orders for the same client</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add a price to an order</td>
    <td>I can keep track of my revenue</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Delete orders</td>
    <td>I can delete cancelled orders</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Edit orders</td>
    <td>I can update the details of my orders</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Find orders by keywords</td>
    <td>I can find a specific order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View all orders</td>
    <td>I can view all my current orders</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Mark an order as done</td>
    <td>I can set the status of an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Mark an order as undone</td>
    <td>I can reverse the changes made to the status of an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Add a recipe</td>
    <td>I can copy details from a recipe to easily fill up the details of an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Delete recipes</td>
    <td>I can delete unused recipes</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Edit recipes</td>
    <td>I can update recipe details, like changing the ingredients used or recipe name</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Find recipes by keywords</td>
    <td>I can reference certain recipes when creating an order</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View all recipes</td>
    <td>I can see all the current recipes used</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>Detect duplicate recipes</td>
    <td>I do not clutter the application with duplicate recipes</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View my most popular recipes</td>
    <td>I can focus on growing my business with the popular recipes</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View my least popular recipes</td>
    <td>I can spend less time refining my skills for those recipes</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View my monthly revenue</td>
    <td>I can tell if my business is growing</td>
  </tr>
  <tr>
    <td>HIGH</td>
    <td>View my most popular clients</td>
    <td>I can reinforce business relationships with my popular clients</td>
  </tr>
</table>


### Use cases

(For all use cases below, the **System** is the `BobTheBistroBoss (BTBB)` and the **Actor** is the `Home Chef`, unless specified otherwise)

**Use case: UC01 Add a client**

**MSS**

1.  Home Chef chooses to add a client.
2.  Home Chef enters details in required format.
3.  BTBB displays new client.

    Use case ends.

**Extensions**

* 2a. Phone number entered already exists in the system.
    * 2a1. BTBB informs Home Chef that client already exist.

      Use case ends.

* 2b. BTBB detects an error in the entered data.
    * 2b1. BTBB informs Home Chef why addition of client failed.

      Use case ends.

**Use case: UC02 Find a client**

**MSS**

1.  Home Chef wants to find a client by a field.
2.  Home Chef enters search terms in required format.
3.  BTBB displays all clients that match the terms.

    Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why finding a client failed.

      Use case ends.

**Use case: UC03 Delete a client**

**MSS**

1.  Home Chef chooses to delete a client.
2.  Home Chef enter details to delete a client.
3.  BTBB informs Home Chef that client has been deleted.

    Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs Home Chef why deletion of client failed.

      Use case ends.

**Use case: UC04 View all clients**

**MSS**

1.  Home Chef chooses to view all clients.
2.  Home Chef enters command to see all clients.
3.  BTBB displays all clients.

    Use case ends.

**Use case: UC05 Edit clients**

**MSS**

1.  Home chef chooses to edit a client’s details.
2.  Home chef enters details in required format.
3.  BTBB displays the edited client.

    Use case ends.

**Extensions**

* 2a. Phone number entered already exists in the system.
    * 2a1. BTBB informs user that client already exist.

      Use case ends.

* 2b. BTBB detects an error in the entered data.
    * 2a1. BTBB informs user why editing of client failed.

      Use case ends.

**Use case: UC06 Add ingredient**

**MSS**

1.  Home chef chooses to add ingredient.
2.  Home chef enters details in required format.
3.  BTBB displays new ingredient.

    Use case ends.

**Extensions**

* 2a. Ingredient entered already exists in the system.
    * 2a1. BTBB informs user that ingredient already exist.

      Use case ends.

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs user why addition of ingredient failed.

      Use case ends.

**Use case: UC07 Find Ingredient**

**MSS**

1.  Home chef wants to find an ingredient by a field.
2.  Home chef enters search terms in required format.
3.  BTBB displays all ingredient that match the format.

    Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs user why finding an ingredient failed.

    Use case ends.

**Use case: UC08 Delete an Ingredient**

**MSS**

1.  Home chef chooses to delete a ingredient.
2.  Home chef enters details in required format.
3.  BTBB displays deleted ingredient.

    Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs user why deletion of ingredient failed.

      Use case ends.

**Use case: UC09 View all ingredients**

**MSS**

1.  Home chef chooses to view all ingredients.
2.  Home chef enters command to see all ingredients.
3.  BTBB displays all ingredients.

    Use case ends.

**Use case: UC10 Edit Ingredient**

**MSS**

1. Home chef chooses to edit an ingredient.
2. Home chef enters details in required format.
3. BTBB displays the edited ingredient.

    Use case ends.

**Extensions**

* 2a. Ingredient entered already exists in the system.
    * 2a1. BTBB informs user that ingredient already exist.

      Use case ends.

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs user why editing of ingredient failed.

      Use case ends.

**Use case: UC11 Add Order**

**MSS**

1. Home chef chooses to add order.
2. Home chef enters details in required format.
3. BTBB displays new order.

    Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs user why addition of order failed.

    Use case ends.

**Use case: UC12 Delete Order**

**MSS**

1. Home chef chooses to delete an order.
2. Home chef enters details in required format.
3. BTBB displays deleted order.

   Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs user why deletion of order failed.

      Use case ends.

**Use case: UC13 Find order**

**MSS**

1. Home chef chooses to find an order.
2. Home chef enters details in required format.
3. BTBB displays all orders that match the format.

   Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs user why finding an order failed.

      Use case ends.

**Use case: UC14 Mark order as done**

**MSS**

1. Home chef chooses to mark an order as done.
2. BTBB displays order as done.

   Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs user why marking an order as done failed.

      Use case ends.

**Use case: UC15 Mark order as undone**

**MSS**

1. Home chef chooses to mark an order as undone.
2. BTBB displays order as undone.

   Use case ends.

**Extensions**

* 2a. BTBB detects an error in the entered data.
    * 2a1. BTBB informs user why marking an order as undone failed.

      Use case ends.

### Non-Functional Requirements

1. Usability Requirements:
    * Should work on systems with Java 11 and above.
    * Should work on Windows, Linux and macOS.

2. Business/Domain Requirements:
    * All quantities in the application are from 0 to 40000.

3. Data Requirements:
    * Data of clients, orders and ingredients should persist after the app closes.

### Glossary

* **Mainstream OS**: Windows, Linux and macOS
* **BTBB**: BobTheBistroBoss
* **Client**: Client information that can be copied to orders
* **Ingredient**: Ingredient information that is stored in inventory and orders
* **Inventory**: Keeps track of ingredients and their quantities
* **Order**: Meal order sold to a client

#### Prefix Glossary

| Prefix | Parameter                | Associated with |
|:------:|:------------------------:|:---------------:|
| c/     | CLIENT_INDEX             | Client          |
| ca/    | CLIENT_ADDRESS           | Client          |
| ce/    | CLIENT_EMAIL             | Client          |
| cn/    | CLIENT_NAME              | Client          |
| cp/    | CLIENT_PHONE             | Client          |
| i/     | INGREDIENT_INDEX         | Ingredient      |
| in/    | INGREDIENT_NAME          | Ingredient      |
| iq/    | INGREDIENT_QUANTITY      | Ingredient      |
| iqf/   | INGREDIENT_QUANTITY_FROM | Ingredient      |
| iqt/   | INGREDIENT_QUANTITY_TO   | Ingredient      |
| iu/    | INGREDIENT_UNIT          | Ingredient      |
| od/    | ORDER_DEADLINE           | Order           |
| of/    | ORDER_FINISHED           | Order           |
| op/    | ORDER_PRICE              | Order           |
| oq/    | ORDER_QUANTITY           | Order           |
| ri/    | RECIPE_INGREDIENT        | Recipe          |
| rn/    | RECIPE_NAME              | Recipe          |

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
