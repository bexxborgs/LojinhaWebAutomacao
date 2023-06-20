package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoDeProdutoPage {
    private WebDriver navegador;

    public FormularioDeAdicaoDeProdutoPage(WebDriver navegador){
        this.navegador = navegador;
    }
    public FormularioDeAdicaoDeProdutoPage InformarNomeDoProduto (String produtoNome){
        // preencher dados do produto e o valor será igual a zero
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);
        return this;
    }
    public FormularioDeAdicaoDeProdutoPage InformarValorDoProduto (String produtoValor){
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);
        return this;
    }
    public FormularioDeAdicaoDeProdutoPage InformarCoresDoProduto (String produtoCor) {
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCor);
        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeAdicaoComErro(){
        navegador.findElement(By.cssSelector("button[type=submit]")).click();

        return new ListaDeProdutosPage(navegador);
    }

    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso(){
        navegador.findElement(By.cssSelector("button[type=submit]")).click();
        return new FormularioDeEdicaoDeProdutoPage(navegador);

    }
}
