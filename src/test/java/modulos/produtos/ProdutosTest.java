package modulos.produtos;
import Paginas.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

@DisplayName ("Teste Web do modulo de produto")
public class ProdutosTest {

        private WebDriver navegador;
    @BeforeEach
    public void beforeEach (){
        // abrir navegador
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty( "webdriver.chrome.driver","C:\\drivers\\chromedriver_win32\\chromedriver.exe");
        this.navegador = new ChromeDriver(options);

        //maximizar a tela
        this.navegador.manage().window().maximize();

        //definir um tempo de espera de 5 seg
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //navevar pela lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2");

    }
    @Test
    @DisplayName("Nao e permitido resgistrar um produtos com o valor igual a zero")
    public void TestNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
        String mensagemApresentada= new LoginPage(navegador)
                .InformarOUsuario("admin")
                .InformarASenha("admin")
                .submeterFormularioDeLogin()
                .AcessarFormularioDeAdicaoDeNovoProduto()
                .InformarNomeDoProduto("Macbook Pro")
                .InformarValorDoProduto("000")
                .InformarCoresDoProduto("preto,branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("Nao E Permitido Registrar Produto Acima De Sete Mil")
    public void testNaoEPermitidoRegistrarProdutoAcimaDeSeteMil () {

        String mensagemApresentada= new LoginPage(navegador)
                .InformarOUsuario("admin")
                .InformarASenha("admin")
                .submeterFormularioDeLogin()
                .AcessarFormularioDeAdicaoDeNovoProduto()
                .InformarNomeDoProduto("Iphone 14")
                .InformarValorDoProduto("700001")
                .InformarCoresDoProduto("preto,azul")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam na faixa de R$0,01  a R$ 7.000,00")
    public void testPossoAdicionarProdutosComValorDeZeroASeteMilReais () {
       String mensagemApresentada = new LoginPage(navegador)
                .InformarOUsuario("admin")
                .InformarASenha("admin")
                .submeterFormularioDeLogin()
                .AcessarFormularioDeAdicaoDeNovoProduto()
                .InformarNomeDoProduto("Kit escolar")
                .InformarValorDoProduto("40000")
                .InformarCoresDoProduto("azul,verde,roxo")
                .submeterFormularioDeAdicaoComSucesso()
               .capturarMensagemApresentada();

                Assertions.assertEquals("Produto adicionado com sucesso",mensagemApresentada);

    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de R$7000")
    public void testPossoAdicionarProdutosComValorSeteMilReais () {
        String mensagemApresentada = new LoginPage(navegador)
                .InformarOUsuario("admin")
                .InformarASenha("admin")
                .submeterFormularioDeLogin()
                .AcessarFormularioDeAdicaoDeNovoProduto()
                .InformarNomeDoProduto("Iphone 15")
                .InformarValorDoProduto("70000")
                .InformarCoresDoProduto("preto, gold")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }
    @AfterEach
    public void afterEach(){
        //vou fechar o navegador
        navegador.quit();
    }

}


