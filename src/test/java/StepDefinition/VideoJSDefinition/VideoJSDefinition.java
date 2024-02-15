package StepDefinition.VideoJSDefinition;

import Page.DriverPage.driverPage;
import Page.ExceptionPage.ExceptionPage;
import Page.WebPage.VideoJSPage;
import Utl.ScenarioContext;
import Utl.Utilidades;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VideoJSDefinition {
    private static final Logger logger = LoggerFactory.getLogger(VideoJSDefinition.class);
    private VideoJSPage videoJSPage;
    public VideoJSDefinition(){
        this.videoJSPage = new VideoJSPage(driverPage.getDriver());
    }

    @Given("ingreso a portal VideoJS")
    public void ingresoAPortalVideoJS() throws Exception {
        String name ="_" + new Object(){}.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo ="";
        try {
            logger.info("Ingreso a portal VideoJS");

            driverPage.getDriver().get("https://videojs.com/advanced/?video=disneys-oceans");

            Assert.assertEquals("Videojs.com homepage", videoJSPage.isVisibleTitleVideoJS());

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado="_OK_";
        }catch (ExceptionPage e){
            estado="_NOOK_";
            throw new ExceptionPage("Error en el page: "+e.getMessage());
        } catch (Exception e){
            estado = "_NOOK_";
            throw new Exception("Error Funcional: "+e.getMessage());
        }finally {
            Utilidades.renameSnapShot(archivo,estado);
        }
    }

    @When("selecciono play al video desplegado")
    public void seleccionoPlayAlVideoDesplegado() throws Exception {
        String name ="_" + new Object(){}.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo ="";
        try {
            logger.info("Selecciono play al video desplegado");

            videoJSPage.clickBtnReproducirVideo();

            Assert.assertTrue(videoJSPage.isPlayingVideo());

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado="_OK_";
        }catch (ExceptionPage e){
            estado="_NOOK_";
            throw new ExceptionPage("Error en el page: "+e.getMessage());
        } catch (Exception e){
            estado = "_NOOK_";
            throw new Exception("Error Funcional: "+e.getMessage());
        }finally {
            Utilidades.renameSnapShot(archivo,estado);
        }
    }

    @Then("valido reproduccion de video")
    public void validoReproduccionDeVideo() throws Exception {
        String name ="_" + new Object(){}.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo ="";
        try {
            logger.info("Validar modal de guardado con exito");

            Assert.assertTrue(videoJSPage.isPlayingVideo());

            videoJSPage.performBarraTareas();
            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado="_OK_";
        }catch (ExceptionPage e){
            estado="_NOOK_";
            throw new ExceptionPage("Error en el page: "+e.getMessage());
        } catch (Exception e){
            estado = "_NOOK_";
            throw new Exception("Error Funcional: "+e.getMessage());
        }finally {
            Utilidades.renameSnapShot(archivo,estado);
        }
    }

    @And("valido que video haya finalizado su reproduccion")
    public void validoQueVideoHayaFinalizadoSuReproduccion() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("Validar modal de guardado con exito");

            videoJSPage.moveToFinalVideo();

            Assert.assertTrue(videoJSPage.isTrueVideoFinalizado());

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @Then("cambio al video {string} de la lista de sugerencias")
    public void cambioAlVideoDeLaListaDeSugerencias(String numero) throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("Validar modal de guardado con exito");
            videoJSPage.clickVideoSeleccionado(numero);

            Assert.assertTrue(videoJSPage.isTrueVideoDesplegadoEsReproducido(numero));
            videoJSPage.performBarraTareas();
            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @When("selecciono play al video desplegado {string}")
    public void seleccionoPlayAlVideoDesplegado(String video) throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");
            videoJSPage.clickCambiarVideoSeleccionado(video);

            Assert.assertEquals("Now Playing", videoJSPage.getIsPlayingVideoSeleccionado(video));

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @And("adelanto el video al minuto {string}")
    public void adelantoElVideoAlMinuto(String minuto) throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");
            videoJSPage.setSegundosVideo(minuto);

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @And("pauso el video desplegado")
    public void pausoElVideo() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");
            videoJSPage.clickBtnPlayPauseVideo();

            Assert.assertTrue(videoJSPage.isPausedVideo());

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @Then("silencio el video desplegado")
    public void silencioElVideoDesplegado() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");
            videoJSPage.clickBtnMuteVideoDesplegado();

            Assert.assertEquals("Unmute", videoJSPage.getIsUnMuteVideoDesplegado());

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @And("subo el volumen a un {string}")
    public void suboElVolumenAUn(String porcentaje) throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");

            videoJSPage.setAumentoDeVolumentoPorcentaje(porcentaje);

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @And("despliego video en Picture in Picture")
    public void despliegoVideoEnPictureInPicture() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");
            videoJSPage.clickBtnReproductorPictureInPicture();

            Assert.assertEquals("Playing in picture-in-picture", videoJSPage.isDisplayedReproductorPictureInPicture());

            videoJSPage.switchToVentanaPictureInPicture();

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @Then("despliego video en pantalla completa")
    public void despliegoVideoEnPantallaCompleta() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");
            videoJSPage.clickBtnReproductorFullScreen();

            Assert.assertEquals("Exit Fullscreen", videoJSPage.isDisplayedReproductorFullScreen());

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @Then("valido que video este disponible para replay")
    public void validoQueVideoEsteDisponibleParaReplay() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");

            Assert.assertEquals("Replay", videoJSPage.getTextBtnReplayVideoDesplegado());

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @And("selecciono Replay al video desplegado")
    public void seleccionoReplayAlVideoDesplegado() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");
            Assert.assertEquals("Replay", videoJSPage.getTextBtnReplayVideoDesplegado());

            videoJSPage.clickBtnReplayVideoDesplegado();
            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @Then("cambio al siguiente video hasta que acabe la lista de recomendacion")
    public void cambioAlSiguienteVideoHastaQueAcabeLaListaDeRecomendacion() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");

            videoJSPage.cambiarATodosVideosDesplegados();

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @And("cambio el idioma de subtitulos a {string}")
    public void cambioElIdiomaDeSubtitulosA(String idioma) throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");

            videoJSPage.clickBtnCaptionsVideoDesplegados();

            videoJSPage.selectCaptionAudioVideoDesplegado(idioma);

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @Then("modifico el Font Size a {string}")
    public void modificoElFontSizeA(String valor) throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");

            videoJSPage.selectOptionFontSize(valor);

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @And("modifico el Text Background a {string}")
    public void modificoElTextBackgroundA(String valor) throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");

            videoJSPage.selectOptionTextBackGround(valor);

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @Then("modifico el Audio Track a {string}")
    public void modificoElA(String audio) throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");

            videoJSPage.clickBtnCaptionsAudioSettings();
            videoJSPage.selectCaptionAudioVideoDesplegado(audio);

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @Then("selecciono Caption Settings")
    public void seleccionoCaptionSettings() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");

            videoJSPage.clickBtnCaptionSettings();

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @Then("seleccion Caption Options")
    public void seleccionCaptionOptions() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");

            videoJSPage.clickBtnCaptionsVideoDesplegados();

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }

    @And("acepto la configuracion de Caption Option")
    public void aceptoLaConfiguracionDeCaptionOption() throws Exception {
        String name = "_" + new Object() {
        }.getClass().getEnclosingMethod().getName();
        String estado = "";
        String archivo = "";

        try {
            logger.info("");

            videoJSPage.clickBtnDoneCaptionSettings();

            archivo = Utilidades.takeSnapShot("/" + ScenarioContext.getInstance().step().toString() + name + ".png");
            estado = "_OK_";
        } catch (ExceptionPage e) {
            estado = "_NOOK_";
            throw new ExceptionPage("Error en el page: " + e.getMessage());
        } catch (Exception e) {
            estado = "_NOOK_";
            throw new Exception("Error Funcional: " + e.getMessage());
        } finally {
            Utilidades.renameSnapShot(archivo, estado);
        }
    }
}
