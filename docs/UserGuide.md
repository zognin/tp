---
layout: page
title: User Guide
---
BobTheBodyBuilder (BTBB) is a **desktop application for private gym managers to manage clients and bookings, optimized for use via a command line interface (CLI).**
Keeping track of information from memberships to booking records for contact tracing can be a hassle if you are a one-man show.
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

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, and `list client`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* The format of all date fields is `dd-mm-yyyy`.

* The format of all time fields is `HHmm`.<br>
  e.g. 1340 is 1.40p.m.

</div>

### Adding a client: `add client`

Adds a client to the application.

Format: `add client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE v/YES_OR_NO m/START_DATE pe/PERIOD`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* <code>p/PHONE_NUMBER</code> is unique to a client. Each phone number in the system must belong to exactly one client.

* <code>v/YES_OR_NO</code> records the client's vaccination status.

* <code>m/START_DATE</code> records the client's membership status. Start date is the start of the membership.

* <code>pe/PERIOD</code> is the time from the start date that the membership is valid in months or years. The period can be in number of months, suffixed by `m` or in years, suffixed by `y`.

* <code>b/BIRTH_DATE</code> and <code>m/START_DATE</code> must follow the format specified [above](#features).

* Please refer to the examples below.

</div>

**Examples:**
* `add client n/Alex Yeoh p/89653101 e/alexyeoh@gmail.com a/Choa Chu Kang St 62 Blk 123 #12-34 b/04-03-1990 v/yes m/04-06-2021 pe/1m` Adds a client named Alex Yeoh, who is vaccinated and membership lasts for 1 month from 4 Jun 2021.

### Deleting a client: `delete client`

Deletes a client from the application.

Format: `delete client INDEX`

**Examples:**
* `delete client 1` Deletes the client at index 1 in the client list currently shown.

### Finding clients by keywords: `find client`

Finds clients whose attribute(s) matches the keyword(s).

Format: `find client [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTH_DATE] [v/YES_OR_NO] [pe/PERIOD_TO_EXP]`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* The search is case-insensitive.

* There must be 1 or more search arguments.

* Partial search will be allowed. <br> 
e.g. <code>find client n/Al</code> can show clients with names like Alice and Alex.

* <code>[b/BIRTH_DATE]</code> must follow the format specified [above](#features).

</div>

**Examples:**
* `find client n/al` Find clients with names matching 'al'. E.g. Alex, Alice, Al.
* `find client n/al p/984` Find clients with names matching ‘al’ and phone numbers matching ‘984’.
* `find client v/yes pe/1y` Find all clients who are vaccinated and whose memberships are expiring in 1 year or less.

### Listing all clients `list client`

Lists all clients in the application.

Format: `list client`

### Adding a booking: `add booking`

Adds a booking for a client to the application. 

Format: `add booking p/PHONE_NUMBER d/DATE s/START_TIME`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* Each booking is associated to one time slot. Time slots are 1 hour 30 minute blocks starting from 0000 hrs. <br>
  e.g. 1st time slot: 0000hrs to 0130hrs, 2nd time slot: 0130hrs to 0300hrs, etc.

* When a booking is added, the nearest time slot to the specified start time is taken as the booking's time slot. <br>

* <code>p/PHONE_NUMBER</code> is the phone number of the client.

* <code>d/DATE</code> and <code>s/START_TIME</code> represents the booking date and time. It must follow the format specified [above](#features).

* Please refer to the examples below.

</div>

**Examples:**
* `add booking p/89653101 d/12-12-2021 s/1030` Adds a booking to the time slot nearest to 1030hrs on 12 December 2021.

### Deleting a booking: `delete booking`

Deletes a booking from the application.

Format: `delete booking INDEX`

**Examples:**
* `delete booking 1` Deletes the booking at index 1 in the booking list currently shown.

### Finding booking by keywords: `find booking`

Finds booking whose attribute(s) matches the keyword(s).

Format: `find booking [d/DATE] [s/START_TIME] [n/NAME] [c/YES_OR_NO]`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* The search is case-insensitive.

* There must be 1 or more search arguments.

* Partial search will be allowed. <br>
  e.g. <code>find booking n/Al</code> can show bookings for clients with names like Alice and Alex.

* <code>c/YES_OR_NO</code> represents the completion status of the booking.

* <code>d/DATE</code> and <code>s/START_TIME</code> represents the booking date and time. It must follow the format specified [above](#features).

* Please refer to the examples below.

</div>

**Examples:**
* `find booking c/yes` Find all completed bookings.
* `find booking n/al` Find bookings for clients with names matching 'al'. E.g. Alex, Alice, Al.
* `find booking d/12-12-2021 s/1930` Find all bookings on 12 December 2021 which starts at 1930 hrs.

### Listing all bookings: `list booking`

Lists all bookings in the application.

Format: `list booking`

### Mark booking as done: `done booking`

Mark booking as done once the client has entered the gym on their booking date and time.

Format: `done booking p/PHONE_NUMBER d/DATE s/START_TIME`

<div markdown="block" class="alert alert-primary">

**:bookmark: Note:**<br>

* <code>p/PHONE_NUMBER</code> is the client's phone number.

* <code>d/DATE</code> and <code>s/START_TIME</code> represents the booking date and time. It must follow the format specified [above](#features).

* Please refer to the examples below.

</div>

**Examples:**
* `done booking p/91231232 d/11-09-2021 s/1930` Marks booking on 11 September 2021, which starts at 1930hrs, made by client with phone number 91231232 as done

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

Action                   | Format and Examples
-------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add client**           | `add client n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE v/YES_OR_NO m/START_DATE pe/PERIOD` <br><br> e.g. `add client n/Alex Yeoh p/89653101 e/alexyeoh@gmail.com a/Choa Chu Kang St 62 Blk 123 #12-34 b/04-03-1990 v/yes m/04-06-2021 pe/1m`
**Delete client**        | `delete client INDEX`
**Find client**          | `find client [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTH_DATE] [v/YES_OR_NO] [pe/PERIOD_TO_EXP]` <br><br> e.g. `find client n/al p/984 v/yes pe/1y`
**List client**          | `list client`
**Add booking**          | `add booking p/PHONE_NUMBER d/DATE s/START_TIME` <br><br> e.g. `add booking p/89653101 d/12-12-2021 s/1030`
**Delete booking**       | `delete booking INDEX`
**Find booking**         | `find booking [d/DATE] [s/START_TIME] [n/NAME] [c/YES_OR_NO]` <br><br> e.g. `find booking d/12-12-2021 s/1930 n/al c/yes`
**List booking**         | `list booking`
**Mark booking as done** | `done booking p/PHONE_NUMBER d/DATE s/START_TIME` <br><br> e.g. `done booking p/91231232 d/11-09-2021 s/1930`
**Help**                 | `help`
