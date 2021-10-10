---
layout: page
title: User Guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## 1. Introduction

### 1.1 About BobTheBistroBoss

BobTheBodyBuilder (BTBB) is a **desktop application for private gym managers to manage clients and orders, optimized for use via a command line interface (CLI).**
Keeping track of information from memberships to order records for contact tracing can be a hassle if you are a one-man show.
That's why, our application centralizes all data in one place, and even comes with a Graphical User Interface (GUI) to easily view and manoeuvre through client and order details.
If you are looking to keep your physique, down to your finger muscles, in shape, give BTBB a try!

## 2. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the jar file of the application.

1. Copy the file to an empty folder. This will be the _home folder_ for BTBB.

1. Double-click the file to start the application.

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
   * **`list client`** : Lists all clients.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add-o cn/CLIENT_NAME`, `CLIENT_NAME` is a parameter which can be used as `add-o cn/John Doe`.

* Items in square brackets are optional.<br>
  e.g `cn/CLIENT_NAME [ri/RECIPE_INGREDIENTS]` can be used as `cn/John Doe ri/Garlic-1-whole` or as `cn/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `cn/CLIENT_NAME cp/CLIENT_PHONE`, `cp/CLIENT_PHONE cn/CLIENT_NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `cp/12341234 cp/56785678`, only `cp/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, and `list client`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* The format of all date fields is `dd-MM-yyyy`.
  e.g. 21-10-1998 is 21 October 1998.

* The format of all time fields is `HHmm`.<br>
  e.g. 1340 is 1.40p.m.

</div>

### 3.1 View help : `help`

Displays all commands and their format.

Format: `help`

### 3.2 Switch Tabs: `tab`

Switches to the specified tab.

Format: `tab INDEX`

* Switches to the tab corresponding to the specified INDEX. INDEX must be 1 or 2.
  * Index 1 corresponds to the Home tab.
  * Index 2 corresponds to the Inventory tab.

Example:

* `tab 1` switches to the Home tab.

### 3.3 Client

#### 3.3.1 Adding a client: `add client`

Adds a client to the application.

Format: `add client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE v/YES_OR_NO m/START_DATE pe/PERIOD`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* <code>p/PHONE_NUMBER</code> is unique to a client. Each phone number in the system must belong to exactly one client.

* <code>v/YES_OR_NO</code> records the client's vaccination status.

* <code>m/START_DATE</code> records the client's membership status. Start date is the start of the membership.

* <code>pe/PERIOD</code> must follow the format specified [above](#features).

* <code>b/BIRTH_DATE</code> and <code>m/START_DATE</code> must follow the format specified [above](#features).

* Please refer to the examples below.

</div>

**Examples:**
* `add client n/Alex Yeoh p/89653101 e/alexyeoh@gmail.com a/Choa Chu Kang St 62 Blk 123 #12-34 b/04-03-1990 v/yes m/04-06-2021 pe/1m` Adds a client named Alex Yeoh, who is vaccinated and membership lasts for 1 month from 4 Jun 2021.

#### 3.3.2 Deleting a client: `delete client`

Deletes a client from the application.

Format: `delete client INDEX`

**Examples:**
* `delete client 1` Deletes the client at index 1 in the client list currently shown.

#### 3.3.3 Finding clients by keywords: `find client`

Finds clients whose attribute(s) matches the keyword(s).

Format: `find client [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTH_DATE] [v/YES_OR_NO] [pe/PERIOD_TO_EXP]`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* The search is case-insensitive.

* There must be 1 or more search arguments.

* Partial search will be allowed. <br>
e.g. <code>find client n/Al</code> can show clients with names like Alice and Alex.

* <code>b/BIRTH_DATE</code> must follow the format specified [above](#features).

* <code>pe/PERIOD_TO_EXP</code> must follow the format specified [above](#features).

</div>

**Examples:**
* `find client n/al` Find clients with names matching 'al'. E.g. Alex, Alice, Al.
* `find client n/al p/984` Find clients with names matching ‘al’ and phone numbers matching ‘984’.
* `find client v/yes pe/1y` Find all clients who are vaccinated and whose memberships are expiring in 1 year or less.

#### 3.3.4 Listing all clients `list client`

Lists all clients in the application.

Format: `list client`

### 3.4 Order

#### 3.4.1 Adding an order: `add-o`

Adds an order to the application.

Format: `add-o c/CLIENT_INDEX cn/CLIENT_NAME cp/CLIENT_PHONE ca/CLIENT_ADDRESS rn/RECIPE_NAME
[ri/RECIPE_INGREDIENTS] rp/RECIPE_PRICE od/DEADLINE [oq/ORDER_QUANTITY]`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* `c/CLIENT_INDEX` will copy over the details of the client at the given index into the order.

* `cn/CLIENT_NAME`, `cp/CLIENT_PHONE` and `ca/CLIENT_ADDRESS` will override any details copied over by
  `c/CLIENT_INDEX`.

* If `c/CLIENT_INDEX` is not specified all of `cn/CLIENT_NAME`, `cp/CLIENT_PHONE`, `ca/CLIENT_ADDRESS` must be
  specified.

* If `cn/CLIENT_NAME`, `cp/CLIENT_PHONE`, `ca/CLIENT_ADDRESS` are all specified, `c/CLIENT_INDEX` does not need to
  be specified.

* Order quantity and recipe ingredients are optional. Order quantity will be set to 1 if not specified.

* Quantity of ingredients in the inventory will decrease by the amount specified after `ri/` if it exists in the
  inventory.

* All orders will be uncompleted upon addition.

* `od/DEADLINE` represents the order deadline date and time. They must follow the format specified [above](#features).

* The format for ingredients `ri/` is `NAME-QTY-UNIT`. <br>
  e.g. Garlic-1-whole.

* Please refer to the examples below.

</div>

**Examples:**
Suppose the first client in the list has the following details:
* Name: John Doe
* Phone: 98765432
* Address: Happy Funland Street 12
* Email: johndoe12@gmail.com

* `add-o c/1 rn/Chicken Rice ri/Chicken-1-whole Rice-200-grams rp/3 od/15-11-2021 1830 oq/1` Adds an order to the
  application where the client's name, phone and address matches the first client in the list shown above. The
  order's recipe name and price will be chicken rice and $3 respectively. The quantity of chicken and rice will
  decrease by 1 whole and 200 grams respectively if it exists in the inventory. The order of 1 chicken rice will be
  scheduled to be delivered by 15 November 2021 at 1830 hrs.

* `add-o cn/Alex cp/98765432 ca/Hogwarts Blk 68 rn/Chicken Rice ri/Chicken-1-whole Rice-200-grams rp/3 od/15-12-2021
  1630 oq/1` Adds an order to the
  application where the client's name, phone and address are Alex, 98765432 and Hogwarts Blk 68 respectively. The
  order's recipe name and price will be chicken rice and $3 respectively. The quantity of chicken and rice will
  decrease by 1 whole and 200 grams respectively if it exists in the inventory. The order of 1 chicken rice will be
  scheduled to be delivered by 15 December 2021 at 1630 hrs.

#### 3.4.2 Editing an order: `edit-o`

Edits an order in the application.

Format: `edit-o INDEX [c/INDEX] [cn/CLIENT_NAME] [cp/CLIENT_PHONE] [ca/CLIENT_ADDRESS] [rn/RECIPE_NAME]
[rp/RECIPE_PRICE] [od/DEADLINE] [oq/QUANTITY]`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* `INDEX` allows you to choose which order to edit by specifying its position in the currently displayed order list.

* `[c/INDEX], [cn/CLIENT_NAME], [cp/CLIENT_PHONE], [ca/CLIENT_ADDRESS], [rn/RECIPE_NAME],
  [rp/RECIPE_PRICE], [od/DEADLINE], [oq/QUANTITY]` allows you to specify the order information to update. None of
  them are mandatory, but at least one must be specified.

</div>

**Examples:**

* `edit-o 1 cn/David` Edits the first order in the list currently shown by changing the client's name to David.
* `edit-o 2 cn/Carol cp/98765432` Edits the second order in the list currently shown by changing the client's name
  to Carol and the client's phone number to 98765432.

#### 3.4.3 Deleting an order: `delete-o`

Deletes an order from the application.

Format: `delete-o INDEX`

**Examples:**
* `delete-o 1` Deletes the order at index 1 in the order list currently shown.

#### 3.4.4 Finding orders by keywords: `find-o`

Find order(s) with attribute(s) that match the keyword(s).

Format: `find-o [cn/CLIENT_NAME] [cp/CLIENT_PHONE] [ca/CLIENT_ADDRESS] [rn/RECIPE_NAME] [od/DEADLINE] [of/YES_OR_NO]`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* The search is case-insensitive.

* There must be 1 or more search arguments.

* Multiple search keywords can be specified for each field. <br>
  e.g. <code>find-o cn/Alex Brian</code>

* Partial search will be allowed. <br>
  e.g. <code>find-o cn/Al</code> can show orders for clients with names like Alice and Alex.

* It will find orders that match at least one keyword, for each prefix.

* `od/DEADLINE` represents the order date and time. They must follow the format specified [above](#features).

* `of/YES_OR_NO` represents whether the order is completed.

* Please refer to the examples below.

</div>

**Examples:**
* `find-o cn/al` Find orders for clients with names matching 'al'. E.g. Alex, Alice, Al.
* `find-o cp/91234567` Find orders for clients with 91234567 as their phone number.
* `find-o cn/Alex David cp/9123 9231` Find orders for clients whose name and phone matches at least 1 of the
  keywords for each prefix. Any orders with the following client details will be matched:
  * Alex 91231100
  * David 91234567
  * Alex 92315697
  * David 92316612

#### 3.4.5 Listing all orders: `list-o`

Lists all orders in the application.

Format: `list-o`

#### 3.4.6 Mark order as done: `done-o`

Mark order as done once it has been delivered to the client.

Format: `done-o INDEX`

**Examples:**
* `done-o 1` Marks the order at index 1 in the order list currently shown as done.

#### 3.4.7 Mark order as undone: `undone-o`

Mark order as undone.

Format: `undone-o INDEX`

**Examples:**
* `undone-o 1` Marks the order at index 1 in the order list currently shown as undone.

#### 3.5 Exiting the program: `exit`

Exits the program.

Format: `exit`

#### 3.6 Saving the data

BTBB data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### 3.7 Editing the data file

BTBB data are saved as a JSON file. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, BTBB will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the application in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BTBB home folder.

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

Action                   | Format and Examples
-------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add client**           | `add client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE v/YES_OR_NO m/START_DATE pe/PERIOD`
**Delete client**        | `delete client INDEX`
**Find client**          | `find client [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTH_DATE] [v/YES_OR_NO] [pe/PERIOD_TO_EXP]`
**List client**          | `list client`
**Add order**          | `add-o c/CLIENT_INDEX cn/CLIENT_NAME cp/CLIENT_PHONE ca/CLIENT_ADDRESS rn/RECIPE_NAME [ri/RECIPE_INGREDIENTS] rp/RECIPE_PRICE od/DEADLINE [oq/ORDER_QUANTITY]`
**Edit order**         | `edit-o INDEX [c/INDEX] [cn/CLIENT_NAME] [cp/CLIENT_PHONE] [ca/CLIENT_ADDRESS] [rn/RECIPE_NAME] [rp/RECIPE_PRICE] [od/DEADLINE] [oq/QUANTITY]`
**Delete order**       | `delete-o INDEX`
**Find order**         | `find-o [cn/CLIENT_NAME] [cp/CLIENT_PHONE] [ca/CLIENT_ADDRESS] [rn/RECIPE_NAME] [od/DEADLINE] [of/YES_OR_NO]`
**List order**         | `list-o`
**Mark order as done** | `done-o INDEX`
**Mark order as undone** | `undone-o INDEX`
**Help**                 | `help`
**Tab**                 | `tab INDEX`
**Exit**                | `exit`
