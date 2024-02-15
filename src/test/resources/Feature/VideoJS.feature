#utf-8
@web @ReproductorVideoJS

Feature: Pruebas realizadas en plataforma

  @1 @reproducircontenido @automatizado @videojs
  Scenario: 01 - Reproducir contenido
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado
    Then valido reproduccion de video

  @1 @verificarreproduccioncontenido @automatizado @videojs
  Scenario: 02 - Verificar que el video esté reproduciendo.
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado
    Then valido reproduccion de video
    And valido que video haya finalizado su reproduccion

  @1 @cambiarvideo2delista @automatizado @videojs
  Scenario: 03 - Cambiar el video a otro 2 video de la lista.
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado
    And valido reproduccion de video
    And valido que video haya finalizado su reproduccion
    And cambio al video "2" de la lista de sugerencias
    Then valido reproduccion de video
    And valido que video haya finalizado su reproduccion

  @1 @cambiartodosvideosdelista @automatizado @videojs
  Scenario: 04 - Cambiar a todos los videos de la lista de recomendación
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado
    Then cambio al siguiente video hasta que acabe la lista de recomendacion


  @1 @adelantovideobigbuckbunny @automatizado @videojs
  Scenario: 05 - Adelantar el video "Big Buck Bunny" al minuto "8:21" y pausarlo
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado "Big Buck Bunny"
    And selecciono play al video desplegado
    And adelanto el video al minuto "8:21"
    Then pauso el video desplegado

  @1 @mutearvideodesplegado @automatizado @videojs
  Scenario: 06 - Silenciar el video "Disney's Oceans"
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado
    And valido reproduccion de video
    Then silencio el video desplegado

  @1 @mutearvideoysubirvolumen @automatizado @videojs
  Scenario: 07 - Silenciar el video "Tears of Steel" y luego subir el volumen al "75%"
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado "Tears of Steel"
    And selecciono play al video desplegado
    And valido reproduccion de video
    Then silencio el video desplegado
    And subo el volumen a un "75%"

  @1 @pantallapicturepicturefullscreen @automatizado @videojs
  Scenario: 08 - Agrandar el video a pantalla Picture in Picture, luego cerrar la pantalla y terminar de reproducir el video en pantalla completa
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado
    And valido reproduccion de video
    And despliego video en Picture in Picture
    Then despliego video en pantalla completa
    And valido que video haya finalizado su reproduccion


  @1 @replayvideodesplegado @automatizado @videojs
  Scenario: 9 - Hacer Replay al video Advanced Bip Bop
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado "Advanced Bip Bop"
    And selecciono play al video desplegado
    And valido reproduccion de video
    And valido que video haya finalizado su reproduccion
    Then valido que video este disponible para replay
    And selecciono Replay al video desplegado
    And valido reproduccion de video
    And valido que video haya finalizado su reproduccion

  @1 @modificarsubtitulosvideo  @automatizado @videojs
  Scenario: 10 - Modificar los subtitulos del video "Advanced Bip Bop"
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado "Advanced Bip Bop"
    And selecciono play al video desplegado
    And valido reproduccion de video
    Then seleccion Caption Options
    Then selecciono Caption Settings
    And modifico el Font Size a "125%"
    And modifico el Text Background a "Yellow"
    And acepto la configuracion de Caption Option
    And cambio el idioma de subtitulos a "Español"
    And valido que video haya finalizado su reproduccion


  @1 @cambiartipoaudio @automatizado @videojs
  Scenario: 11 - cambiar el audio del video "Advanced Bip Bop"
    Given ingreso a portal VideoJS
    When selecciono play al video desplegado "Advanced Bip Bop"
    And selecciono play al video desplegado
    And valido reproduccion de video
    Then modifico el Audio Track a "BipBop Audio 1"
    And valido que video haya finalizado su reproduccion



