---
layout: page
title: Ajay's Project Portfolio Page
---

### Project: BobTheBistroBoss

BobTheBistroBoss (BTBB) is a desktop application for home chefs to manage their orders and inventory,
optimized for use via a command line interface (CLI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to find a client. (Pull request [#70](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/70))
    * What it does: Allows the users to find clients by their names, email, address or phone number.
    * Justification: If the users have many clients, the find clients command enables them to filter the list of
      clients so that the users can reference their client index more easily when adding an order.
    * Highlights: The find client command enables partial search for client names and phone numbers. For client names, the
      search is case-insensitive.

* **New Feature**: Added the ability to edit/delete an ingredient. (Pull requests [#80](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/80), [#83](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/83))
    * What it does: Allows the users to edit or delete ingredients in their inventory.
    * Justification: Allows the user to have a more direct control of the ingredients in the inventory if
      changes need to be made to the inventory such as during a restocking.
    * Highlights: The edit ingredients command allows users to edit the name, quantity or unit of an ingredient.

* **New Feature**: Added the recipe model and view
    * What it does: Allows the users to store and view information related to their recipes.
    * Justification: Allows the users to copy recipes so that they do not have to specify the full
      list of ingredients when adding orders, making the process more efficient.
    * Highlights: The recipe model allows users to store the recipe name, ingredients and price, all of which can be
      copied over when creating an order.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Manoharan-Ajay-Anand&tabRepo=AY2122S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Team tasks**:
    * Maintained the issue tracker by creating issues and closing issues.
    * Added css style classes for image captions used in documentations.
* **Reviewing/mentoring contributions**:
    * PRs reviewed:
      [#163](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/163),
      [#85](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/85),
      [#69](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/69),
      [#155](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/155),
      [#74](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/74)
    * Helped team members with finding entities implementation. Helped team members to code reusable
      generic classes to help find any entity by their fields.

* **Documentation**:
    * User Guide:
      * Added a sample tutorial under the quick start section to enable users to learn how to use our application
        faster. (Pull request [#232](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/232))
      * Added labelled images with captioning to the user guide for users to understand our application better.
        (Pull request [#177](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/177))
      * Added delete recipe ingredient feature to user guide. (Pull request [#174](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/174))
      * Added list recipe feature to user guide. (Pull request [#172](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/172))
    * Developer Guide:
      * Added manual testing instructions for recipe to developer guide. (Pull request [#255](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/255))
      * Added edit feature implementation and corresponding UML diagrams to developer guide. (Pull request [#187](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/187))
      * Added find feature implementation and corresponding UML diagrams to developer guide. (Pull request [#121](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/121))

* **Community**:
    * Reported bugs and suggestions for other teams: [Reported 15 bugs for PED](https://github.com/Manoharan-Ajay-Anand/ped/issues)
