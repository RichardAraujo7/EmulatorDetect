Este projeto é um aplicativo Android desenvolvido para detectar quando um dispositivo está sendo executado em um emulador. O aplicativo utiliza várias estratégias de detecção para identificar sinais de emulação e retorna uma pontuação com base na probabilidade de o dispositivo ser um emulador.

## Sumário

- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Detecção de Emulador](#detecção-de-emulador)
- [Interface do Usuário](#interface-do-usuário)
- [Como Contribuir](#como-contribuir)
- [Licença](#licença)

## Funcionalidades

- **Detecção Multiestratégia**: Utiliza uma combinação de verificações para detectar a presença de um emulador.
- **Interface Amigável**: Fornece uma interface de usuário intuitiva e organizada para exibir os resultados de detecção.
- **Resultados Detalhados**: Mostra detalhes sobre as características que levaram à detecção de emulador.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal usada para o desenvolvimento.
- **Jetpack Compose**: Usado para construir a interface de usuário do aplicativo.
- **Material3**: Framework de design utilizado para fornecer um visual moderno e responsivo.

## Estrutura do Projeto

O projeto é organizado em módulos para facilitar a manutenção e extensão:

- **detection**: Contém a lógica principal para detecção de emuladores, incluindo estratégias individuais.
- **ui**: Contém a interface de usuário, construindo telas e interações do aplicativo.
- **core**: Contém classes e utilitários básicos que são utilizados em todo o projeto.

## Detecção de Emulador

O aplicativo utiliza diversas estratégias para detectar emuladores, cada uma atribuindo uma pontuação baseada em sua relevância. Aqui estão algumas das estratégias implementadas:

1. **BuildConfigEmuDetector**: Verifica informações de configuração de build conhecidas de emuladores.
2. **FilesEmuDetector**: Procura por arquivos específicos que são comumente encontrados em emuladores.
3. **VirtualizationEmuDetector**: Detecta sinais de virtualização.
4. **NetworkEmuDetector**: Analisa as características da rede para padrões de emulador.
5. **SensorEmuDetector**: Verifica a presença de sensores que normalmente não estão presentes em emuladores.
6. **SystemPropertiesEmuDetector**: Analisa propriedades do sistema para detectar emuladores.
7. **CpuModelEmuDetector**: Verifica o modelo de CPU para identificar emulação.
8. **ScreenResolutionEmuDetector**: Avalia a resolução da tela para inconsistências com dispositivos físicos.
9. **BackgroundProcessesEmuDetector**: Detecta processos de fundo que são típicos em ambientes de emulação.
10. **AppSignaturesEmuDetector**: Analisa assinaturas de aplicativos para suspeitas de emulação.
11. **LibrariesEmuDetector**: Verifica bibliotecas conhecidas por serem usadas em emuladores.
12. **RootDetectionEmuDetector**: Procura por arquivos de root típicos em emuladores.
13. **FileSystemEmuDetector**: Verifica diretórios específicos para detectar emulação.
14. **ServiceDetectionEmuDetector**: Analisa serviços do sistema para padrões de emuladores.
15. **APKCheckerEmuDetector**: Analisa o APK para detectar pacotes de emuladores conhecidos.
16. **SystemFeatureEmuDetector**: Verifica recursos do sistema para inconsistências.

Cada estratégia contribui para a pontuação total, que determina se o dispositivo é identificado como um emulador.

## Interface do Usuário

A interface do usuário é projetada para ser clara e intuitiva, com as seguintes características:

- **EmulatorDetectionScreen**: Exibe os resultados da detecção, incluindo pontuação, detalhes de sensores e câmeras.
- **MainScreen**: Tela principal do aplicativo, fornecendo um resumo das detecções e opções para o usuário.

---

Se você precisar de alguma seção adicional ou ajustes específicos para atender às necessidades do seu projeto, por favor, avise.
