# stock-records
It is part of Open Source project (APACHE license) for managing stock records. Feel free to join the development efforts and contribute to build a great product.

**Project background** - Banks are building their own products or using third party products for offering various services to bank users. 
These products often involves huge cost to build & maintain or integrate & license and does not offer any advantage over competitor, due to commodity nature of services of such product. Such cost often leads to higher price point of services to the banks user.
The goal of the open source project is to build commodity products as open-source and enable banks to focus on differentiating capabilities and at the end, offer basic products and services to end-user at reasonable price.

**Project lead** - [Sandeep Kumar](https://www.linkedin.com/in/krsandeep/)

Please refer to [Rules of engagement] for effective collaboration.


## Overview
Stock records is used for managing Safekeeping Positions, Movements and Blockings of Securities at a custodian.

### Main entities
- **Position** - A position is a holding of a security at a custodian. It is identified by a unique combination of ISIN, account and subaccount. A position can be blocked for pledge or other reasons.
- **Movements** - A movement is a change in a position. It is identified by a unique combination of ISIN, account, subaccount, movement date and movement type. A movement can be a receipt, delivery, pledge, unpledge, etc.
- **Blocking** - A blocking is a restriction on a position. It is identified by a unique combination of ISIN, account, subaccount, blocking date and blocking type. A blocking can be a pledge, mortgage, etc.

### Main operations
- **Update position** - Creates a new position or updates an existing position for a security at a custodian.
- **Update movement** - Creates a new movement or updates an existing movement for a position.
- **Update blocking** - Create a new blocking or updates an existing blocking for a position.
- **Get position** - Get the current position for a security at a custodian.
- **Get movements** - Get all movements for a position.
- **Get blockings** - Get all blockings for a position.
- **Get position history** - Get the history of a position.
- **Get movements history** - Get the history of movements for a position.
- **Get blockings history** - Get the history of blockings for a position.
- **Get positions** - Get all positions for a security at a custodian.

### Services
- **PositionService** - Service for all operations related to positions 
- **MovementService** - Service for all operations related to movements
- **BlockingService** - Service for all operations related to blockings
- **BookService** - Service for all operations related to booking of movements to positions
- 
### Pending topics

- [ ] Booking date in Positions and Transactions
- [ ] Settlement date in Transactions
- [ ] Attributes about ownership - pledged, mortgage unique no etc
- [ ] Asset class attribute such as denomination
- [ ] Audit trail of changes - all entities
- [ ] Support for as per date and availability
- [ ] implementation of blocking entities and services

### Rules of engagement
* Constructive discussion are most welcome, transparent and open communication is the key - no hidden agenda & finger pointing
* All code should be formatted using [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
* All code should be covered by unit tests
* All code changes should be done in a feature branch and then merged to master branch via pull request
* All pull requests should be reviewed by at least one other developer
* All pull requests should pass the build and tests
* All pull requests should be squashed and merged
* All pull requests should be linked to an issue or pending topic
* Active participation in the project is expected from all contributors, incl. discussion and code reviews
* All contributors should be added to the [CONTRIBUTORS.md](CONTRIBUTORS.md) file
