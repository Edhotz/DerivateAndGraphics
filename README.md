# Gráficos JavaFX

Biblioteca baseada na API JavaFX para construção de gráficos de funções matemáticas e conjuntos de pontos.

## Informações gerais
Dependência maven:
<pre><code>&lt;dependency&gt;
    &lt;groupId&gt;io.github.mauriciophysics&lt;/groupId&gt;
    &lt;artifactId&gt;Graficos&lt;/artifactId&gt;
    &lt;version&gt;1.7&lt;/version&gt;
&lt;/dependency&gt;</code></pre>

É possível exibir o gráfico em uma Stage ou em um AnchorPane, através da sobrecarga do método <code>show()</code><br/>
Para salvar uma imagem do gráfico, basta clicar com o botão direito do mouse e escolher a opção no menu!

## Gráfico de função
<pre><code>Funcao f = x -> Math.sin(x);
Grafico g = new Grafico();
g.plotFuncao(f, 0, 2*Math.PI, "Seno");
g.show(stage);</pre></code>

![Gráfico de função](https://github.com/mauriciophysics/GraficosJavaFX/blob/master/imagens/GraficoDeFuncao.png)

## Gráfico de pontos
<pre><code>Double[] x = {1.0, 2.2, 3.84, 4.9, 5.6, 6.2};
Double[] y = {2.24, 3.71, 4.5, 5.96, 8.48, 16.8};
Grafico g = new Grafico();
g.plotPontos(x, y, "Pontos", Estilo.LINHA_E_MARCADOR);
g.show(stage);</code></pre>

![Gráfico de pontos](https://github.com/mauriciophysics/GraficosJavaFX/blob/master/imagens/GraficoDePontos.png)

## Linha de tendência
<pre><code>Double[] x = {1.0, 2.2, 3.84, 4.9, 5.6, 6.2};
Double[] y = {2.24, 3.71, 4.5, 5.96, 8.48, 16.8};
Grafico g = new Grafico();
g.plotPontos(x, y, "Pontos", Estilo.MARCADOR, LinhaDeTendencia.QUADRATICA);
g.show(stage);</code></pre>

![Gráfico de pontos com linha de tendência](https://github.com/mauriciophysics/GraficosJavaFX/blob/master/imagens/GraficoDePontosComLinhaDeTendencia.png)

A linha de tendência pode ser personalizada, através do padrão Builder:
<pre><code>Double[] x = {1.0, 2.2, 3.84, 4.9, 5.6, 6.2};
Double[] y = {2.24, 3.71, 4.5, 5.96, 8.48, 16.8};
Grafico g = new Grafico();
LinhaDeTendencia linear = new LinhaDeTendencia.Builder(POLINOMIAL)
	.setTitulo("Ajuste linear")
	.setExibirR2(true)
	.setExibirSigma2(true)
	.build();
g.plotPontos(x, y, "Pontos", Estilo.MARCADOR, linear);
g.show(stage);</code></pre>

![Gráfico de pontos com linha de tendência personalizada](https://github.com/mauriciophysics/GraficosJavaFX/blob/master/imagens/GraficoDePontosComLinhaDeTendenciaPersonalizada.png)

## Estilos
<pre><code>Double[] x = {1.0, 2.2, 3.84, 4.9, 5.6, 6.2};
Double[] y = {2.24, 3.71, 4.5, 5.96, 8.48, 16.8};
Grafico g = new Grafico();
Estilo estilo = new Estilo.Builder()
	.setExibirLinha(false)
	.setCor(DARKBLUE)
	.build();
Estilo estiloLinhaTendencia = new Estilo.Builder()
	.setCor(DARKRED)
	.setEstiloLinha(TRACEJADA)
	.build();
LinhaDeTendencia quadratica = new LinhaDeTendencia.Builder(POLINOMIAL)
	.setGrau(2)
	.setEstilo(estiloLinhaTendencia)
	.build();
g.plotPontos(x, y, "Pontos", estilo, quadratica);
g.show(stage);</code></pre>

![Gráfico de pontos com linha de tendência estilizada](https://github.com/mauriciophysics/GraficosJavaFX/blob/master/imagens/GraficoDePontosComLinhaDeTendenciaEstilizada.png)
# DerivateAndGraphics
