<p align="center"> <img src="/src/main/resources/LOGO%20ESCURO.png" alt="Equipe bytech"/></p>
<br>
<p align="center">
  <samp>
    <a href="#o-projeto">Projeto</a> ▪️
    <a href="#proposta">Proposta</a> ▪️
    <a href="#tecnologias">Tecnologias</a> ▪️
    <a href="#cronograma-das-sprints">Cronograma das Sprints</a> ▪️
    <a href="#product-backlog">Product Backlog</a> ▪️
    <a href="#sprint-backlog">Sprints Backlog</a> ▪️
    <a href="#burndown-das-sprints">Burndown das sprints</a> ▪️
    <a href="#detalhes-das-sprints">Detalhes das Sprints</a> ▪️
    <a href="#equipe">Equipe</a>
    
  </samp>
</p>

<br>

<h1 align="center"><samp>O PROJETO</samp></h1>

![Equipe bytech](/src/main/resources/Objetivo.png)

<br>
<h1 align="center"><samp>PROPOSTA</samp></h1>

O presente projeto tem por objetivo desenvolver um sistema desktop capaz de armazenar informações cadastrais visando a automatização de processos de conciliação financeira.

### 📖 Requisitos funcionais
+ - [x] Cadastro de canais (marketplaces e meios de pagamentos)
+ - [x] Configuração de canais (usuário/senha e token)
+ - [x] Configurações ativas

### 🔖 Requisitos não funcionais
+ - [x] Linguagem Java
+ - [x] Banco de Dados Relacional
+ - [X] Documentações

<br>

<h1 align="center"><samp>TECNOLOGIAS</samp></h1>

![Equipe bytech](/src/main/resources/Tecnologia.png)

<table align="center">
  <tr>
    <th><b>Front-end</b></th>
    <th><b>Back-end</b></th>
    <th><b>Ferramentas</b></th>
  </tr>
  <tr>
    <td>JavaFX</td>
    <td>Java</td>
    <td>MySQL</td>
  </tr>
  <tr>
    <td></td>
    <td>SQL</td>
    <td>Figma</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td>Git</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td>Github</td>
  </tr>
   <tr>
    <td></td>
    <td></td>
    <td>Scene Builder</td>
  </tr>
</table>

<br>
<h1 align="center"><samp>CRONOGRAMA DAS SPRINTS</samp></h1>

![Equipe bytech](/src/main/resources/Cronograma%20das%20Sprints.png)

<br>
<h1 align="center"><samp>PRODUCT BACKLOG</samp></h1>

![Equipe bytech](/src/main/resources/Product%20Backlog.png)

<h1 align="center"><samp>SPRINT BACKLOG</samp></h1>

![Equipe bytech](/src/main/resources/Sprint%20Backlog.png)

<br>
<h1 align="center"><samp>BURNDOWN DAS SPRINTS</samp></h1>

<br>
<h1 align="center"><samp>PRIMEIRA SPRINT</samp></h1>

![Equipe bytech](/src/main/resources/Burndown_1.png)

<br>
<h1 align="center"><samp>SEGUNDA SPRINT</samp></h1>

![Equipe bytech](/src/main/resources/Burndown_1.png)

<br>
<h1 align="center"><samp>DETALHES DAS SPRINTS</samp></h1>
<h2>Sprint 1</h2>
<details>
  <summary>Detalhes</summary>
  <h3 align="center">Demonstração de usabilidade</h3>
   <br>
  <h4 align="center">Tela Cadastro de Canais<br><a href="https://www.youtube.com/watch?v=UtJIXQ2DS-o">Youtube (Qualidade melhor)</a></h4>
  <p align="center">
    <img src=" /src/main/resources/Cadastro_canais_adm.gif " width="65%" />
  </p>
  <p align="justify">Demonstração da tela de cadastro de canais e aplicação no banco de dados.Esta interface contempla a execução do cadastro de canais (p.ex. Mercado Livre, Americanas), tipo de canal (p.ex. Marketplace) e o padrão de autenticação (Usuário/senha ou Token). Bem como, a aplicação do banco de dados – armazenamento das informações cadastrais. </p>
  
 <br>
  <h4 align="center">Tela de Configurações de Canais – Usuário e Senha <br><a href="https://www.youtube.com/watch?v=s-h25q8byrY">Youtube (Qualidade melhor)</a></h4>
  <p align="center">
    <img src=" /src/main/resources/Config_canais_user.gif " width="65%" />
  </p>
  <p align="justify">Demonstração da tela de configurações de canais versão Usuário e Senha. Esta interface abrange a configuração do canal por meio de um Usuário e Senha.</p>
  
 <br>
  <h4 align="center">Tela de Configurações de Canais – Token <br><a href="https://www.youtube.com/watch?v=Ayp1KyIrV_s">Youtube (Qualidade melhor)</a></h4>
  <p align="center">
    <img src=" /src/main/resources/Config_canais_token.gif " width="65%" />
  </p>
  <p align="justify">Demonstração da tela de configurações de canais versão Token. Esta interface abrange a configuração do canal por meio de um Token.</p>

<br>
  <h4 align="center">Tela de Configurações Ativas<br><a href="https://www.youtube.com/watch?v=PDNR33mpV70">Youtube (Qualidade melhor)</a></h4>
  <p align="center">
    <img src=" /src/main/resources/Config_ativas.gif " width="65%" />
  </p>
  <p align="justify">Demonstração da tela de configurações ativas. Esta interface contempla todas as configurações já realizadas e oferece para o cliente a opção de consulta e edição (p.ex. Editar e Deletar) dos canais já cadastrados.</p>
  
<br>
  <h3 align="center">Modelo de dados relacional</h3>
  <h4 align="center">Modelo conceitual<br></h4>
  <p align="center">
    <img src="/src/main/resources/Diagramtrackcash.bmp" width="65%" />
  </p>
  <p align="justify">A princípio foram identificadas as seguintes entidades: <i>defaultChannels</i>; <i>users</i>; <i>registeredChannelLogin</i>; <i>registeredChannelToken</i>. A entidade <i>defaultChannels</i> contêm informações sobre o canal (chave primária), nome, tipo e padrão de autenticação. A entidade <i>users</i> abriga informações relativas aos usuários (chave primária), nome, e-mail, senha, telefone, documento e tipo de usuário. A entidade <i>registeredChannelLogin</i> contêm informações dos canais do tipo de autenticação usuário/senha, sendo o atributo <i>registeredChannelLogin_id</i> a chave primária; <i>user_id</i> chave estrangeira da tabela <i>users</i>; e <i>channel_id</i> chave estrangeira da tabela <i>defaultChannels</i>. Por fim, <i>registeredChannelToken</i> contempla o tipo de autenticação token, na qual o atributo <i>registeredChannelToken_id</i> a chave primária; <i>user_id</i> chave estrangeira da tabela <i>users</i>; e <i>channel_id</i> chave estrangeira da tabela <i>defaultChannels</i>.</p>
  
  <br>
  <h4 align="center">Modelo lógico<br></h4>
  <p align="center">
    <img src="/src/main/resources/apiTrackCashERDiagrama.png" width="65%" />
  </p>
  <p align="justify">O modelo de dados lógico é caracterizado pelas entidades: <i>defaultChannels</i>; <i>users</i>; <i>registeredChannelLogin</i>; <i>registeredChannelToken</i>. A entidade <i>defaultChannels</i> contêm os seguintes atributos: <i>channel_id</i> (chave primária) do tipo inteiro, <i>name</i> do tipo baseado em caracteres, <i>type</i> do tipo caracteres; e <i>auth</i> baseado em carateres. A entidade <i>users</i> abriga os atributos: <i>user_id</i> (chave primária) do tipo inteiro, <i>name</i> do tipo baseado em caracteres, <i>email</i> do tipo baseado em caracteres, <i>password</i> do tipo baseado em caracteres, <i>phone</i> do tipo baseado em inteiro, <i>document</i> do tipo baseado em caracteres e <i>type_adm</i> do tipo baseado em caracteres. A entidade <i>registeredChannelLogin</i> contêm informações dos canais do tipo de autenticação usuário/senha, sendo o atributo <i>registeredChannelLogin_id</i> a chave primária do tipo inteiro; <i>login</i> do tipo baseado em caracteres; <i>password</i> do tipo baseado em caracteres; <i>user_id</i> chave estrangeira da tabela <i>users</i>; e <i>channel_id</i> chave estrangeira da tabela <i>defaultChannels</i>. Por fim, <i>registeredChannelToken</i> contempla o tipo de autenticação token, na qual o atributo <i>registeredChannelToken_id</i> a chave primária do tipo inteiro; <i>token</i> do tipo inteiro; <i>user_id</i> chave estrangeira da tabela <i>users</i>; e <i>channel_id</i> chave estrangeira da tabela <i>defaultChannels</i>.</p>
    <p align="justify">As relações entre as entidades são:</p>
    <ul>
      <li align="justify"><b>1:N</b> - <i>defaultChannels</i> se associa a muitas ocorrências da entidade <i>registeredChannelToken</i>, mas <i>registeredChannelToken</i> pode se associar a uma ocorrência da entidade <i>defaultChannels</i></li>
      <li align="justify"><b>1:N</b> - <i>defaultChannels</i> se associa a muitas ocorrências da entidade <i>registeredChannelLogin</i>, mas <i>registeredChannelLogin</i> pode se associar a uma ocorrência da entidade <i>defaultChannels</i></li>
      <li align="justify"><b>1:N</b> - <i>users</i> se associa a muitas ocorrências da entidade <i>registeredChannelToken</i>, mas <i>registeredChannelToken</i> pode se associar a uma ocorrência da entidade <i>users</i></li>
      <li align="justify"><b>1:N</b> - <i>users</i> se associa a muitas ocorrências da entidade <i>registeredChannelLogin</i>, mas <i>registeredChannelLogin</i> pode se associar a uma ocorrência da entidade <i>users</i></li>
    </ul>
</details>
  
<br>
<h1 align="center"><samp>EQUIPE</samp></h1>

<table align="center">
  <tr>
    <th><b>Nome</b></th>
    <th><b>Função</b></th>
    <th><b>Github</b></th>
    <th><b>Linked-In</b></th>
  </tr>
    <tr>
    <td>João Henrique</td>
    <td>Product Owner</td>
    <td><a href="https://github.com/JoaoHenrique7">Github</a></td>
    <td><a href="https://www.linkedin.com/in/jo%C3%A3o-henrique-trist%C3%A3o-b63385207/">Linked-In</a></td>
  </tr>
   <tr>
    <td>Micael Leal</td>
    <td>Scrum Master</td>
    <td><a href="https://github.com/micael-leal">Github</a></td>
    <td><a href=""></a></td>
  </tr>
    <tr>
    <td>Camila Redondo</td>
    <td>Desenvolvedor</td>
    <td><a href="https://github.com/CamilaRedondo">Github</a></td>
    <td><a href="https://www.linkedin.com/in/camila-silveira-redondo-7941631ab/">Linked-In</a></td>
  </tr>
  <tr>
    <td>Gustavo Marques</td>
    <td>Desenvolvedor</td>
    <td><a href="https://github.com/gusta7597">Github</a></td>
    <td><a href="https://www.linkedin.com/in/gustavo-marques-lima-695b331a2/">Linked-In</a></td>
  </tr>
    <tr>
    <td>Henrique Neto</td>
    <td>Desenvolvedor</td>
    <td><a href="https://github.com/henriqFerreira">Github</a></td>
    <td><a href="https://www.linkedin.com/in/henriquepfneto/">Linked-In</a></td>
  </tr>
  <tr>
    <td>Leandro Aquino</td>
    <td>Desenvolvedor</td>
    <td><a href="https://github.com/leandroteixeira97">Github</a></td>
    <td><a href="https://www.linkedin.com/in/leandroteixeira97/">Linked-In</a></td>
  </tr>
  <tr>
    <td>Simone Kanzawa</td>
    <td>Desenvolvedor</td>
    <td><a href="https://github.com/Simonehk">Github</a></td>
    <td><a href=""></a></td>
  </tr>
  <tr>
    <td>Yago Pereira</td>
    <td>Desenvolvedor</td>
    <td><a href="https://github.com/YagoPSilva">Github</a></td>
    <td><a href="https://www.linkedin.com/in/yago-pereira21/">Linked-In</a></td>
  </tr>

</table>

