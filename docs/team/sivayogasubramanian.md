---
layout: page
title: Sivayogasubramanian's Project Portfolio Page
---

## Project: BobTheBistroBoss

### Overview

BobTheBistroBoss (BTBB) is a desktop application for home chefs to manage their orders and inventory,
optimized for use via a command line interface (CLI).

### Summary of Contributions

* **New Feature**: Added the ability to add an order. (Pull request [#84](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/84)).
  * **What it does**: Allows the user to add an order.
  * **Justification**: The user would want to add orders to BTBB as the orders come in to take note of the client and recipe details.
    Furthermore, the user would also want to also take note of the order price, order deadline and the order quantity.
  * **Highlights**: When adding an order, the user can add recipe ingredients. If the ingredients are present in the inventory,
    it will automatically deduct the appropriate quantity from the inventory depending on the amount used for a particular order.

* **New Feature**: Added the ability edit an order. (Pull request [#94](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/94)).
  * **What it does**: Allows the user to edit an order.
  * **Justification**: Users might want to make changes to an order after they have added it to BTBB.
  * **Highlights**: When editing an order, the user can change the order quantity. If the order has recipe ingredients and if they are present in the inventory,
    it will automatically add or deduct the appropriate quantity from the inventory depending on the changes made to the order quantity.

* **New Feature**: Added a bar chart to show the revenue earned for the past twelve months. (Pull request [#131](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/131)).
  * **What it does**: Allows the user to view their past revenue for the past twelve months in a bar chart format.
  * **Justification**: This feature allows users to make smarter business decisions depending on their performance for the past twelve months.
    It also allows the user to easily identity trends and patters in the revenue.
  * **Highlights**: JavaFX bar chart together with an observable list was used to implement this feature. The bar chart
    is constantly updated whenever an order is completed.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=sivayogasubramanian&tabRepo=AY2122S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Team tasks**:
  * Set up the GitHub organisation and team repository. Enabled pre-push githooks (Pull Request [#1](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/1)).
  * Set up the codebase so that the team can implement new features in a consistent manner and in parallel.
    (Pull requests [#59](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/59)).
  * Maintain the issue tracker by creating issues, adding labels and closing issues. Created several epic issues for better organization.

* **Review/mentoring contributions**:
  * PRs reviewed: [#34](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/34),
    [#66](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/66),
    [#70](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/70),
    [#83](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/83),
    [#95](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/95),
    [#160](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/160).
  * Helped team members with implementation questions.

* **Documentation**:
  * User Guide:
    * Added user guide for delete recipe. (Pull Request [#150](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/150)).
    * Made user guide more user-centric. (Pull Request [#247](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/247)).
  * Developer Guide:
    * Added use cases. (Pull Request [#96](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/96)).
    * Added implementation details for the adding order. (Pull Request [#140](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/140)).
    * Added some manual testing instructions. (Pull Request [#248](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/248)).
  * General:
    * Added product screenshots (Pull Request [#152](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/152),
      [#171](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/171),
      [#237](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/237)).

* **Community**:
  * Forum responses: [#52](https://github.com/nus-cs2103-AY2122S1/forum/issues/52),
    [#135](https://github.com/nus-cs2103-AY2122S1/forum/issues/135),
    [#146](https://github.com/nus-cs2103-AY2122S1/forum/issues/146).
