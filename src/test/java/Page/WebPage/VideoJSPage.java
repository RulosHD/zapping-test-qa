package Page.WebPage;

import Page.BasePage.basePage;
import Page.ExceptionPage.ExceptionPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.Iterator;
import java.util.Set;

public class VideoJSPage extends basePage {
    public VideoJSPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "(//a[@class='Link__StyledLink-sc-wxg1ap-0 hLBjTn Link-sc-wxg1ap-1 iHtQlw'])[1]")
    private WebElement titleVideoJS;

    @FindBy(xpath = "//button[@title='Play Video']")
    private WebElement btnReproducirVideo;

    @FindBy(xpath = "//button[@class='vjs-play-control vjs-control vjs-button vjs-playing']")
    private WebElement btnValidarReproduccion;

    @FindBy(xpath = "//div[@class='vjs-progress-holder vjs-slider vjs-slider-horizontal']")
    private WebElement valueLenghtVideo;

    @FindBy(id = "preview-player_html5_api")
    private WebElement inputVideoDesplegado;

    @FindBy(xpath = "//button[@class='vjs-play-control vjs-control vjs-button vjs-paused vjs-ended']")
    private WebElement btnValidarFinReproduccion;

    @FindBy(xpath = "//button[@class='vjs-play-control vjs-control vjs-button vjs-paused']")
    private WebElement btnValidarPauseReproduccion;

    @FindBy(xpath = "//button[@class='vjs-mute-control vjs-control vjs-button vjs-vol-3']")
    private WebElement btnMuteVideoDesplegado;

    @FindBy(xpath = "//button[@class='vjs-mute-control vjs-control vjs-button vjs-vol-0']")
    private WebElement btnUnMuteVideoDesplegado;

    @FindBy(xpath = "//div[@class='vjs-volume-bar vjs-slider-bar vjs-slider vjs-slider-horizontal']")
    private WebElement sliderVolumeVideoDesplegado;

    @FindBy(xpath = "//button[@class='vjs-picture-in-picture-control vjs-control vjs-button']")
    private WebElement btnReproductorPictureInPicture;

    @FindBy(xpath = "//button[@class='vjs-fullscreen-control vjs-control vjs-button']")
    private WebElement btnReproductorFullScreen;

    @FindBy(xpath = "//ol[@class='vjs-playlist-item-list']")
    private WebElement contendorVideosListaSugerencias;

    @FindBy(xpath = "//button[@class='vjs-subs-caps-button vjs-menu-button vjs-menu-button-popup vjs-button']")
    private WebElement btnCaptionsVideoDesplegado;

    @FindBy(xpath = "//li[@class='vjs-menu-item vjs-texttrack-settings']")
    private WebElement btnCaptionSettingsVideoDesplegado;

    @FindBy(xpath = "//button[@class='vjs-done-button']")
    private WebElement btnDoneCaptionSettings;

    @FindBy(xpath = "//button[@class='vjs-audio-button vjs-menu-button vjs-menu-button-popup vjs-button']")
    private WebElement btnCaptionAudioSettings;

    public String isVisibleTitleVideoJS() throws Exception {
        try{
            waitUntilElementIsVisible(titleVideoJS);

            if(!isVisible(titleVideoJS)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!titleVideoJS.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            moveToElement(titleVideoJS);
            return titleVideoJS.getAttribute("aria-label");

        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickBtnReproducirVideo() throws Exception {
        try{
            waitUntilElementIsVisible(btnReproducirVideo);

            if(!isVisible(btnReproducirVideo)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnReproducirVideo.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            moveToElement(btnReproducirVideo);
            btnReproducirVideo.click();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean isPlayingVideo() throws Exception {
        try{
            waitUntilElementIsVisible(btnValidarReproduccion);
            if(!isVisible(btnValidarReproduccion)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnValidarReproduccion.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            moveToElement(btnValidarReproduccion);
            return btnValidarReproduccion.isDisplayed();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void moveToFinalVideo() throws Exception {//Funcion que mueve la repoduccion hacia la cantidad de segundos restantes solicitados
        try{
            waitUntilElementIsVisible(inputVideoDesplegado);
            if(!isVisible(inputVideoDesplegado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!inputVideoDesplegado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            Actions actions = new Actions(getDriver());
            actions.clickAndHold(inputVideoDesplegado).perform();

            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].currentTime = arguments[1]", inputVideoDesplegado, setUltimosSegundosVideo());
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String setUltimosSegundosVideo() throws Exception {//Funcion que obtiene el valor de la duracion total en minutos, retornadola en segundos
        try{
            String duracionVideoReproducido;
            String[] arrayDuracionVideo;
            int duracionSegundosVideoReproducido;

            waitUntilElementIsVisible(valueLenghtVideo);

            if(!isVisible(valueLenghtVideo)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!valueLenghtVideo.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            duracionVideoReproducido = valueLenghtVideo.getAttribute("aria-valuetext").split("of")[1].trim();
            arrayDuracionVideo = duracionVideoReproducido.split(":");
            duracionSegundosVideoReproducido = (Integer.parseInt(arrayDuracionVideo[0]) * 60 + Integer.parseInt(arrayDuracionVideo[1]) - 10);

            return String.valueOf(duracionSegundosVideoReproducido);
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean isTrueVideoFinalizado() throws Exception {//Valida si la reproduccion finalizo
        try{
            waitForElementToDisappear(btnValidarReproduccion);
            waitUntilElementIsVisible(btnValidarFinReproduccion);
            if(!isVisible(btnValidarFinReproduccion)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnValidarFinReproduccion.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            return btnValidarFinReproduccion.isDisplayed();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickVideoSeleccionado(String numeroVideo) throws Exception {//Selecciona video asigado por Gherkin
        try{
            By selector = By.xpath(String.format("(//ol[@class='vjs-playlist-item-list']/li)[%s]", numeroVideo));
            WebElement videoSeleccionado = getDriver().findElement(selector);
            if(!isVisible(videoSeleccionado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!videoSeleccionado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            videoSeleccionado.click();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Boolean isTrueVideoDesplegadoEsReproducido(String numeroVideo) throws Exception {//Valida que el video desplegado se esta reproduciendo
        try{
            By selector = By.xpath(String.format("(//span[@title='Now Playing'])[%s]", numeroVideo));
            WebElement videoSeleccionado = getDriver().findElement(selector);
            if(!isVisible(videoSeleccionado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!videoSeleccionado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            return videoSeleccionado.isDisplayed();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickCambiarVideoSeleccionado(String nombreVideo) throws Exception {//Cambia al video seleccionado por Gherkin
        try{
            By selector = By.xpath(String.format("//cite[@title='%s']", nombreVideo));
            WebElement videoSeleccionado = getDriver().findElement(selector);
            if(!isVisible(videoSeleccionado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!videoSeleccionado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            videoSeleccionado.click();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String getIsPlayingVideoSeleccionado(String nombreVideo) throws Exception {//Funcion que retorna el atributo title para validar el cambio de video
        try{
            By selector = By.xpath(String.format("//cite[@title='%s']//parent::div//parent::picture/span", nombreVideo));
            WebElement videoSeleccionado = getDriver().findElement(selector);

            waitUntilElementIsVisible(videoSeleccionado);
            if(!isVisible(videoSeleccionado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!videoSeleccionado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            return videoSeleccionado.getAttribute("title");

        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void setSegundosVideo(String minutos) throws Exception {//Funcion que setea adelantar minuto segun segundos indicados
        try{
            waitUntilElementIsVisible(inputVideoDesplegado);
            if(!isVisible(inputVideoDesplegado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!inputVideoDesplegado.isEnabled()) throw new ExceptionPage("Elemento no disponible");


            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].currentTime = arguments[1]", inputVideoDesplegado, setSegundosVideoPorMinutos(minutos));
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String setSegundosVideoPorMinutos(String minutos) throws Exception {//Funcion que transforma los minutos entregados y los retorna en segundos
        try{
            int duracionSegundosVideoReproducido;

            waitUntilElementIsVisible(valueLenghtVideo);
            if(!isVisible(valueLenghtVideo)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!valueLenghtVideo.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            duracionSegundosVideoReproducido = (Integer.parseInt(minutos.split(":")[0]) * 60 + Integer.parseInt(minutos.split(":")[1]));

            return String.valueOf(duracionSegundosVideoReproducido);
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickBtnPlayPauseVideo() throws Exception {//Funcion que transforma los minutos entregados y los retorna en segundos
        try{
            waitUntilElementIsVisible(btnValidarReproduccion);
            if(!isVisible(btnValidarReproduccion)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnValidarReproduccion.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            btnValidarReproduccion.click();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean isPausedVideo() throws Exception {
        try{
            waitUntilElementIsVisible(btnValidarPauseReproduccion);
            if(!isVisible(btnValidarPauseReproduccion)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnValidarPauseReproduccion.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            moveToElement(btnValidarPauseReproduccion);
            return btnValidarPauseReproduccion.isDisplayed();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickBtnMuteVideoDesplegado() throws Exception {
        try{
            waitUntilElementIsVisible(btnMuteVideoDesplegado);
            if(!isVisible(btnMuteVideoDesplegado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnMuteVideoDesplegado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            btnMuteVideoDesplegado.click();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String getIsUnMuteVideoDesplegado() throws Exception {
        try{
            waitUntilElementIsVisible(btnUnMuteVideoDesplegado);
            if(!isVisible(btnUnMuteVideoDesplegado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnUnMuteVideoDesplegado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            return btnUnMuteVideoDesplegado.getAttribute("title");
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void setAumentoDeVolumentoPorcentaje(String porcentaje) throws Exception {//Funcion que calcula los puntos a mover en el slide de volumen segun porcentaje establecido en Gherkin
        try{
            int volumenTotal = 42;
            float volumenFinal;
            int puntoFinal;

            waitUntilElementIsVisible(btnUnMuteVideoDesplegado);
            waitUntilElementIsVisible(sliderVolumeVideoDesplegado);

            if(!isVisible(btnUnMuteVideoDesplegado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnUnMuteVideoDesplegado.isEnabled()) throw new ExceptionPage("Elemento no disponible");
            if(!isVisible(sliderVolumeVideoDesplegado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!sliderVolumeVideoDesplegado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            Actions actions = new Actions(getDriver());
            actions.moveToElement(btnUnMuteVideoDesplegado);
            actions.moveToElement(sliderVolumeVideoDesplegado);
            actions.clickAndHold(sliderVolumeVideoDesplegado);

            volumenFinal = volumenTotal*(Float.parseFloat(porcentaje.replace("%",""))/100);

            puntoFinal = (int) (volumenFinal - 21);

            actions.moveByOffset(puntoFinal,0);
            actions.perform();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickBtnReproductorPictureInPicture() throws Exception {
        try{
            waitUntilElementIsVisible(btnReproductorPictureInPicture);
            if(!isVisible(btnReproductorPictureInPicture)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnReproductorPictureInPicture.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            btnReproductorPictureInPicture.click();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void switchToVentanaPictureInPicture() throws Exception {//Funcion que cierra la ventana de Picture in Picture
        try{
            String parent=getDriver().getWindowHandle();
            Set<String> s=getDriver().getWindowHandles();
            Iterator<String> I1= s.iterator();
            String child_window;

            waitFor(5);
            while(I1.hasNext())
            {
                child_window=I1.next();
                if(!parent.equals(child_window)) {
                    getDriver().switchTo().window(child_window).close();
                    getDriver().switchTo().window(parent);
                    break;
                }
            }
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String isDisplayedReproductorPictureInPicture() throws Exception {//Funcion que valida la reproduccion en modo Picture in Picture
        try{
            WebElement btnGetText = getDriver().findElement(By.xpath("//p[@class='vjs-pip-text']"));
            waitUntilElementIsVisible(btnGetText);

            return btnGetText.getText();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickBtnReproductorFullScreen() throws Exception {
        try {
            waitUntilElementIsVisible(btnReproductorFullScreen);
            if(!isVisible(btnReproductorFullScreen)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnReproductorFullScreen.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            btnReproductorFullScreen.click();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String isDisplayedReproductorFullScreen() throws Exception {//Funcion que valida reproduccion en modo Fullscreen
        try{
            WebElement btnGetText = getDriver().findElement(By.xpath("//button[@class='vjs-fullscreen-control vjs-control vjs-button']/span[2]"));
            waitUntilElementIsVisible(btnGetText);
            if(!isVisible(btnGetText)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnGetText.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            return btnGetText.getText();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String getTextBtnReplayVideoDesplegado() throws Exception {//Funcion que valida estado replay
        try{
            waitUntilElementIsVisible(btnValidarFinReproduccion);
            if(!isVisible(btnValidarFinReproduccion)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnValidarFinReproduccion.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            WebElement btnGetText = btnValidarFinReproduccion.findElement(By.xpath("./span[2]"));
            waitUntilElementIsVisible(btnGetText);

            return btnGetText.getText();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickBtnReplayVideoDesplegado() throws Exception {
        try{
            waitUntilElementIsVisible(btnValidarFinReproduccion);
            if(!isVisible(btnValidarFinReproduccion)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnValidarFinReproduccion.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            btnValidarFinReproduccion.click();
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void cambiarATodosVideosDesplegados() throws Exception {//Funcion que valida que exista un video siguiente, y desplegara los siguientes videos a aparecer por 5 segundos
        try{
            waitUntilElementIsVisible(contendorVideosListaSugerencias);
            int numOfRow = contendorVideosListaSugerencias.findElements(By.xpath("./li")).size();
            for (int i=2; i <= numOfRow - 1; i++){
                WebElement siguienteVideoADesplegar = contendorVideosListaSugerencias.findElement(By.xpath("./li["+i+"]/picture/div/span"));
                waitUntilElementIsVisible(siguienteVideoADesplegar);
                if (siguienteVideoADesplegar.getText().equalsIgnoreCase("Up Next")){
                    WebElement btnUpNextVideo = contendorVideosListaSugerencias.findElement(By.xpath("./li["+i+"]/picture"));
                    waitUntilElementIsVisible(btnUpNextVideo);
                    scrollDownToElement(btnUpNextVideo);
                    waitFor(5);
                    btnUpNextVideo.click();
                }
            }
        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void selectOptionFontSize(String option) throws Exception {//Funcion que ejecuta por js el select option, parseando el porcentaje a decimal para setear el option por value
        try{

            float value = Float.parseFloat(option.replace("%",""))/100;

            ((JavascriptExecutor)getDriver()).executeScript("const select = document.querySelector(\"#vjs_select_608\"); select.value=arguments[0]", value);

        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void selectOptionTextBackGround(String option) throws Exception {//Funcion que setea segun entrada String por Gherkin el value en codigo para cada color de background en subtitulos
        try{
            switch (option){
                case "Yellow":
                    option = "#FF0";
                    break;
            }

            ((JavascriptExecutor)getDriver()).executeScript("const select = document.querySelector(\"#vjs_select_604\"); select.value=arguments[0]", option);

        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickBtnDoneCaptionSettings() throws Exception {
        try{
            waitUntilElementIsVisible(btnDoneCaptionSettings);

            if(!isVisible(btnDoneCaptionSettings)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnDoneCaptionSettings.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            btnDoneCaptionSettings.click();

        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void selectCaptionAudioVideoDesplegado(String option) throws Exception {
        try{
            WebElement optionAudio = getDriver().findElement(By.xpath("//span[text()='" + option + "']"));
            waitUntilElementIsVisible(optionAudio);

            if(!isVisible(optionAudio)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!optionAudio.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            optionAudio.click();
            waitFor(5);

        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickBtnCaptionSettings() throws Exception {
        try{
            waitUntilElementIsVisible(btnCaptionSettingsVideoDesplegado);

            if(!isVisible(btnCaptionSettingsVideoDesplegado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnCaptionSettingsVideoDesplegado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            btnCaptionSettingsVideoDesplegado.click();

        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickBtnCaptionsVideoDesplegados() throws Exception {
        try{
            waitUntilElementIsVisible(btnCaptionsVideoDesplegado);

            if(!isVisible(btnCaptionsVideoDesplegado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnCaptionsVideoDesplegado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            btnCaptionsVideoDesplegado.click();

        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void clickBtnCaptionsAudioSettings() throws Exception {
        try{
            waitUntilElementIsVisible(btnCaptionAudioSettings);

            if(!isVisible(btnCaptionAudioSettings)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnCaptionAudioSettings.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            btnCaptionAudioSettings.click();

        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void performBarraTareas() throws Exception {
        try{
            waitUntilElementIsVisible(btnMuteVideoDesplegado);

            if(!isVisible(btnMuteVideoDesplegado)) throw new ExceptionPage("Elemento no visible en la pagina");
            if (!btnMuteVideoDesplegado.isEnabled()) throw new ExceptionPage("Elemento no disponible");

            Actions actions = new Actions(getDriver());
            actions.moveToElement(btnMuteVideoDesplegado).build().perform();

        }catch (ExceptionPage e){
            throw new ExceptionPage(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
