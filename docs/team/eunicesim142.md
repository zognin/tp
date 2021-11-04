---
layout: page
title: Eunice's Project Portfolio Page
---

## Project: BobTheBistroBoss

### Overview

BobTheBistroBoss (BTBB) is a desktop application for home chefs to manage their orders and inventory,
optimized for use via a command line interface (CLI).

### Summary of Contributions

* **New Feature**: Added the ability to add an ingredient. (Pull request [#69](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/69))
  * **What it does**: Allows the user to add an ingredient to their inventory.
  * **Justification**: Users would want to manage a multitude of ingredients. This feature will allow them to
    add ingredients they wish to track to an inventory, so that BTBB can manage the tracking for them.
  * **Highlights**: The quantity field in an Ingredient can be automatically added or deducted, depending on the amount used in a particular order.

* **New Feature**: Added the ability to mark an order as done or undone. (Pull Request [#93](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/93))
  * **What it does**: Allows the user to mark an order as either done or undone.
  * **Justification**: Users can easily keep track of orders that are incomplete.
  * **Highlights**: Order list will automatically sort completed and uncompleted orders, displaying all uncompleted orders at the top of the list.
  A green checkmark indicates the order is completed and no checkmark indicates the order is incomplete.

* **New Feature**: Added a pie chart to show the user's top 10 most popular recipes. (Pull Request [#134](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/134))
  * **What it does**: BTBB automatically counts the number of times each recipe has been ordered and displays the top 10 recipes
    with the highest number of  in a pie chart.
  * **Justification**: The user can easily see their most popular recipes to gain insight of which dishes to continue selling and which need further development.
  * **Highlights**: JavaFX pie chart together with an observable list was used to implement this feature. The pie chart
    is updated whenever an order has been added, deleted or edited.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=EuniceSim142&tabRepo=AY2122S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Team tasks**:
  * Made the release of `v1.4` on Github.
  * Maintained the issue tracker by creating and closing issues.
  * Reviewed documentation before final submission, to ensure cohesiveness of different sections in UG and DG.

* **Review/mentoring contributions**:
  * PRs reviewed: [#117](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/117),
    [#129](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/129),
    [#176](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/176),
    [#225](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/225)

* **Enhancements to existing features**:
  * Refactor codebase by replacing 'Person' with 'Client'. (Pull Request [#44](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/44))
  * Abstract out all String classes that use the same regex, into one GenericString class, to reduce code duplication. 
    These include all name fields and Ingredient Unit. (Pull Request [#69](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/69))

* **Documentation**:
    * User Guide:
      * Added section for orders related to clients and ingredients. (Pull Request [#91](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/91))
      * Update 'About BTBB' section of User Guide, to make it more user-centric. (Pull Request [#242](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/242))
      * Add find recipe feature. (Pull Request [#170](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/170))
    * Developer Guide:
      * Added implementation details for list commands. (Pull Request [#132](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/132))
      * Added implementation details for statistics. (Pull Request [#173](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/173))
      * Added some manual testing instructions. (Pull Request [#246](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/246))
   * General:
      * Implement skeleton 'About Us' page. (Pull Request [#18](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/18))

* **Community**:
  * Reported bugs and suggestions for other teams in the class: [Reported 18 bugs during PE-D](https://github.com/EuniceSim142/ped/issues)
