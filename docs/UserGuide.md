---
layout: page
title: User Guide
---
BobTheBodyBuilder (BTBB) is a **desktop app for private gym managers to manage clients and bookings, optimized for use via a command line interface (CLI).** 
Keeping track of information from memberships to booking records for contact tracing can be a hassle if you are a one man show. 
That's why, our app centralizes all data in one place, and even comes with a Graphical User Interface (GUI) to easily view and manoeuvre through client and booking details.
If you are looking to keep your physique, down to your finger muscles, in shape, give BTBB a try!

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the jar file of the app

1. Copy the file to an empty folder. This will be the _home folder_ for BTBB.

1. Double-click the file to start the app.

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list client`** : Lists all clients.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [e/EMAIL]` can be used as `n/John Doe e/john@gmail.com` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, and `list client`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
  
* The format of all date fields is `dd-mm-yyyy`.

* The format of all time fields is `HHmm`.<br>
  e.g. 1340 is 1.40p.m.

</div>

### Adding a client: `add client`

Adds a client to the app.

Format: `add client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE v/YES_OR_NO m/START_DATE PERIOD`

<div markdown="span" class="alert alert-primary">:bookmark: **Note:**

* <code>v/YES_OR_NO</code> records the client's vaccination status.

* <code>m/START_DATE PERIOD</code> records the client's membership status.<br> 
Start date is the start of the membership.<br> 
Period is the time from the start date that the membership is valid in months or years.<br> 
The period can be in number of months, suffixed by `m` or in years, suffixed by `y`.

* Please refer to the examples below.

</div>

Examples:
* `add client n/Alex Yeoh p/89653101 e/alexyeoh@gmail.com a/Choa Chu Kang St 62 Blk 123 #12-34 b/04031990 v/yes m/04062021 1m` Adds a client named Alex Yeoh, who is vaccinated and membership lasts for 1 month from 4 Jun 2021.

### Deleting a client: `delete client`

Deletes a client from the app.

Format: `delete client INDEX`

Examples:
* `delete client 1` Deletes the client at index 1 in the current shown client list.

### Finding clients by keywords: `find client`

Finds clients whose attribute(s) matches a keyword.

Format: `find client [n/KEYWORD] [p/KEYWORD] [e/KEYWORD] [a/KEYWORD] [b/KEYWORD] [v/YES_OR_NO] [m/PERIOD_TO_EXP]`

<div markdown="span" class="alert alert-primary">:bookmark: **Note:**

* The search is case insensitive.

* There must be 1 or more search arguments.

* Partial search will be allowed. <br> 
e.g. <code>find client n/Al</code> can show clients with names like Alice and Alex.

</div>

Examples:
* `find client n/al` Find clients with names matching 'al'. E.g. Alex, Alice, Al.
* `find client n/al p/984` Find clients with names matching ‘al’ and phone numbers matching ‘984’.
* `find client v/y m/1y` Find all clients who are vaccinated and whose memberships are expiring in 1 year or less.

### Listing all clients `list`

Lists all clients in the app.

Format: `list`

### Viewing help : `help`

Displays all commands and their format.

Format: `help`

### Saving the data

BTBB data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

BTBB data are saved as a JSON file. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, BTBB will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BTBB home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add client** | `add client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE v/YES_OR_NO m/START_DATE PERIOD` <br> e.g. `add client n/Alex Yeoh p/89653101 e/alexyeoh@gmail.com a/Choa Chu Kang St 62 Blk 123 #12-34 b/04031990 v/yes m/04062021 1m`
**Delete client** | `delete client INDEX`
**Find client** | `find client [n/KEYWORD] [p/KEYWORD] [e/KEYWORD] [a/KEYWORD] [b/KEYWORD] [v/YES_OR_NO] [m/PERIOD_TO_EXP]`<br> e.g. `find client n/al`
**List client** | `list client`
**Help** | `help`
