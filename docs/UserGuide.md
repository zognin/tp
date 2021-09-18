---
layout: page
title: User Guide
---
BobTheBodyBuilder (BTBB) is a **desktop application for private gym managers to manage clients and bookings, optimized for use via a command line interface (CLI).**
Keeping track of information from memberships to booking records for contact tracing can be a hassle if you are a one man show.
That's why, our application centralizes all data in one place, and even comes with a Graphical User Interface (GUI) to easily view and manoeuvre through client and booking details.
If you are looking to keep your physique, down to your finger muscles, in shape, give BTBB a try!

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the jar file of the application

1. Copy the file to an empty folder. This will be the _home folder_ for BTBB.

1. Double-click the file to start the application.

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

Adds a client to the application.

Format: `add client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE v/YES_OR_NO m/START_DATE PERIOD`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

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

Deletes a client from the application.

Format: `delete client INDEX`

Examples:
* `delete client 1` Deletes the client at index 1 in the client list currently shown.

### Finding clients by keywords: `find client`

Finds clients whose attribute(s) matches a keyword.

Format: `find client [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTH_DATE] [v/YES_OR_NO] [m/PERIOD_TO_EXP]`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* The search is case insensitive.

* There must be 1 or more search arguments.

* Partial search will be allowed. <br> 
e.g. <code>find client n/Al</code> can show clients with names like Alice and Alex.

</div>

Examples:
* `find client n/al` Find clients with names matching 'al'. E.g. Alex, Alice, Al.
* `find client n/al p/984` Find clients with names matching ‘al’ and phone numbers matching ‘984’.
* `find client v/y m/1y` Find all clients who are vaccinated and whose memberships are expiring in 1 year or less.

### Listing all clients `list client`

Lists all clients in the application.

Format: `list client`

### Adding a booking: `add booking`

Adds a booking for a client to the application.

Format: `add booking p/PHONE_NUMBER s/SLOT_NUMBER`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* <code>p/PHONE_NUMBER</code> is the phone number of the client.

* <code>s/SLOT_NUMBER</code> is the time slot number which the client needs to specify.<br>

Exhaustive list of time slot numbers: 

Time slot number | Time period
:---------------:|:------------------:
1                | 0000 hrs - 0130 hrs
2                | 0130 hrs - 0300 hrs
3                | 0300 hrs - 0430 hrs
4                | 0430 hrs - 0600 hrs
5                | 0600 hrs - 0730 hrs
6                | 0730 hrs - 0900 hrs
7                | 0900 hrs - 1030 hrs
8                | 1030 hrs - 1200 hrs
9                | 1200 hrs - 1330 hrs
10               | 1330 hrs - 1500 hrs
11               | 1500 hrs - 1630 hrs
12               | 1630 hrs - 1800 hrs
13               | 1800 hrs - 1930 hrs
14               | 1930 hrs - 2100 hrs
15               | 2100 hrs - 2230 hrs
16               | 2230 hrs - 0000 hrs


* Please refer to the examples below.

</div>

Examples:
* `add booking p/89653101 s/7` Adds a booking at 0900hrs to 1030hrs for the client who has 89653101 as their phone number.

### Deleting a booking: `delete booking`

Deletes a booking from the application.

Format: `delete booking INDEX`

Examples:
* `delete booking 1` Deletes the client at index 1 in the booking list currently shown.

### Finding booking by keywords: `find booking`

Finds booking whose attribute(s) matches a keyword.

Format: `find booking [d/DATE] [t/START_TIME] [n/NAME] [s/STATUS]`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* The search is case insensitive.

* There must be 1 or more search arguments.

* Date and time must follow the format specified [above](#features). 

* Partial search will be allowed. <br>
  e.g. <code>find booking n/Al</code> can show bookings for clients with names like Alice and Alex.

* A booking either has no status (which represents a booking that is not completed) or a completed status.

</div>

Examples:
* `find booking n/al` Find bookings for clients with names matching 'al'. E.g. Alex, Alice, Al.
* `find booking d/12-12-2021 t/1930` Find all bookings on 12 December 2021 which starts at 1930 hrs.
* `find booking s/c` Find all completed bookings.

### Listing all bookings: `list booking`

Lists all bookings in the application.

Format: `list booking`

### Mark booking as done: `done booking`

Mark booking as done once the client has entered the gym on their time slot

Format: `done booking d/DATE s/SLOT_NUMBER p/PHONE_NUMBER`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* Date must follow the format specified [above](#features).

* <code>p/PHONE_NUMBER</code> is the client's phone number.

* <code>s/SLOT_NUMBER</code> is the booking's time slot number.

</div>

Examples:
* `done booking d/11092021 s/12 p/91231232` Marks booking on 11 September 2021, with a time slot number 12, made by client with phone number 91231232 as done

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
**A**: Install the application in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BTBB home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action                   | Format, Examples
-------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add client**           | `add client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE v/YES_OR_NO m/START_DATE PERIOD` <br> e.g. `add client n/Alex Yeoh p/89653101 e/alexyeoh@gmail.com a/Choa Chu Kang St 62 Blk 123 #12-34 b/04031990 v/yes m/04062021 1m`
**Delete client**        | `delete client INDEX`
**Find client**          | `find client [n/KEYWORD] [p/KEYWORD] [e/KEYWORD] [a/KEYWORD] [b/KEYWORD] [v/YES_OR_NO] [m/PERIOD_TO_EXP]`<br> e.g. `find client n/al`
**List client**          | `list client`
**Add booking**          | `add booking p/PHONE_NUMBER s/SLOT_NUMBER`
**Delete booking**       | `delete booking INDEX`
**Find booking**         | `find booking [d/DATE] [t/START_TIME] [n/NAME] [s/STATUS]`
**List booking**         | `list booking`
**Mark booking as done** | `done booking d/DATE s/SLOT_NUMBER p/PHONE_NUMBER`
**Help**                 | `help`
