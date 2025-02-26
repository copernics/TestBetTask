#Current Implementations:
Separation of Concerns – The ViewModel handles logic, while the Activity observes state.
State Management with StateFlow – Ensures reactive UI updates.
Repository Pattern – Decouples data sources from the UI; allows easy addition of network calls, caching, or database integration.
  Also, unit testing-friendly.
Immutable Bet Object – Improves safety and predictability.
Encapsulation – No raw strings; all data is structured and type-safe.
Implemented Version Catalog (libs.versions.toml) – Simplifies dependency management and updates.
Refactored following Clean Architecture principles and adopted the MVI pattern for the presentation layer.
Data Layer has its own data model, ensuring separation from domain logic.
Migrated UI to Jetpack Compose.
Implemented Dependency Injection with Koin.
Used Coil for image loading – smooth experience and positive results.
Added Unit and UI Tests.


##########################
# Next Steps & Improvements:
#Enhance Repository Layer :

Implement support for multiple data sources:
Local storage (Room/Shared Preferences) + Remote API.
Improve error handling & caching strategies.
Introduce WebSocket Communication

A betting app typically requires real-time updates via WebSocket API.

#Prepare for Future Modularization

Structure the project with standard modules:
Core (common utilities & base components)
Features (business logic & UI for different sections)
UI Components (shared design system & reusable UI elements)
Testing (unit & UI test support)
