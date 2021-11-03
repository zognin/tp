---
layout: page
title: Imran's Project Portfolio Page
---

## Project: BobTheBistroBoss

### Overview
BobTheBistroBoss (BTBB) is a desktop application for home chefs to manage their orders and inventory, optimized for use via a command line interface (CLI).

### Summary of Contributions

* **New feature**: Added functionality for users to find orders using various parameters. (Pull requests [#79](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/79), [#98](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/98))
  * **What it does**: Allows the user to find orders using one or more of the following parameters: client name, phone
    number, address, recipe name, order deadline and completion status.
  * **Justification**: This feature increases the user's ease of finding orders in BTBB.
  * **Highlights**: This feature necessitated the implementation of a generic list to store different types of
    predicates used to filter the order list. These predicates were created to facilitate the find order command.

* **New feature**: Added functionality to copy client and recipe bookmark details to orders. (Pull requests [#67](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/67), [#137](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/137))
  * **What it does**: Allows users to quickly add orders by using the client and recipe indexes shown in the list
    instead of typing all the details.
  * **Justification**: It can be troublesome for users to constantly key in the full details of a frequent client and
    popular recipe when adding an order.
    This feature enables users to add the details of a frequent client or popular recipe first and reuse those details when adding an order.
    This hastens the process of adding an order.
  * **Highlights**: This feature significantly reduces the amount of characters a user has to type to add an order.

* **New feature**: Added a pie chart to display a user's top 10 most frequent clients (Pull request [#120](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/120))
  * **What it does**: BTBB automatically counts the amount of orders for each client and displays the top 10 clients
    with the most number of orders in a pie chart.
  * **Justification**: This feature allows users to reinforce business relations with their most frequent clients. It
    also removes the hassle of manually counting the orders to find out who the most frequent clients are.
  * **Highlights**: JavaFX pie chart together with an observable list was used to implement this feature. The pie chart
    is updated whenever there are changes to orders.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Imranr2&tabRepo=AY2122S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Team tasks**:
  * Closed milestone `v1.1`.
  * Maintained the issue tracker by creating and closing issues.
  * Ensured the team is on track to complete tasks by given deadline.

* **Review/mentoring contributions**
  * PRs reviewed: [#93](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/93),
    [#94](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/94),
    [#96](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/96),
    [#248](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/248)
  * Helped team members with styling issues for JavaFX charts.

* **Documentation**:
  * User Guide
    * Added section for commands related to orders. [#82](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/82)
    * Added section explaining the statistics shown in BTBB. [#159](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/159)

  * Developer Guide
    * Added implementation details for the delete command. [#130](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/130)
    * Added implementation details for the copying of client and recipe bookmarks to orders. [#165](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/165)
    * Updated user story and use cases. [#225](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/225)
    * Updated all architecture and class diagrams to match BTBB's implementation. [#231](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/231)
    * Added some manual testing instructions. [#241](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/241)

* **Community**:
  * Reported bugs and suggestions for other teams in the class: [Reported 18 bugs during PE-D](https://github.com/Imranr2/ped/issues)
