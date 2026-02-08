# Lab3

Este laboratorio se divide en dos partes y tiene como objetivo aplicar conceptos de **arquitectura limpia**, **manejo de estado** y **separación de responsabilidades** usando **Jetpack Compose** y **MVVM**.

---

## Parte 1

En la Parte 1 se implementó el sistema de **favoritos** en el `HomeScreen`, permitiendo marcar y desmarcar canciones como favoritas y reflejar el cambio directamente en la interfaz.

**Video explicación Parte 1:**  
https://youtu.be/zT7N7N2Z3Tc

### DOD (Definition of Done)

- ✅ **Model updated**  
  `Song` has `isFavorite: Boolean = false` field.

- ✅ **Heart icon visible**  
  Each `SongCard` shows heart icon (filled / outlined).

- ✅ **Event hoisting**  
  `SongCard` receives `onFavoriteClick: (String) -> Unit`.

- ✅ **ViewModel handles**  
  `toggleFavorite(id: String)` method implemented in ViewModel.

- ✅ **State updates**  
  Clicking the heart updates the song's favorite status.

- ✅ **UI reflects change**  
  Heart icon toggles between filled / outlined.

- ✅ **No state in child**  
  `SongCard` is stateless (receives data, emits events).

---

## Parte 2

En la Parte 2 se creó una **nueva pantalla** que muestra únicamente las canciones favoritas.  
Esta pantalla es accesible mediante un botón en la **barra de navegación**.

**Video explicación Parte 2:**  
https://youtu.be/GBeV8jryfC4

### DOD (Definition of Done)

- ✅ **Route exists**  
  `HighlightsDestination` defined in `Destinations.kt`.

- ✅ **Screen created**  
  `HighlightsScreen.kt` displays filtered favorites.

- ✅ **Bottom nav visible**  
  `NavigationBar` with 2 `NavigationBarItem`.

- ✅ **Tab switching works**  
  Tapping tabs navigates between screens.

- ✅ **Correct icons**  
  Home icon for Home, Star icon for Highlights.

- ✅ **State shared**  
  Favoriting on Home reflects on Highlights immediately.

- ✅ **Current tab highlighted**  
  Active tab is visually distinguished.
