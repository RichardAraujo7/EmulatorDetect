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


RESULTADOS EMULADORES

BlueStacks // Tela de login e pós tentativa de login

![bluestacks_tela_login](https://github.com/user-attachments/assets/69fd9819-2ba0-472c-8b96-610565e5076f)

![bluestacks_emulador_detectado](https://github.com/user-attachments/assets/4ff28f7a-19b3-4e26-94f1-aee8c3733877)

Genymotion // Tela de login e pós tentativa de login

![genymotion_tela_login](https://github.com/user-attachments/assets/1a78b5b9-1bdc-48de-b890-605e09d957f4)

![genymotion_emulador_detectado](https://github.com/user-attachments/assets/c44afb8c-36dd-4c4f-806e-c1b3a68b1ca4)

Android-x86 // Tela de login e pós tentativa de login

![android_x86_tela_login](https://github.com/user-attachments/assets/da804426-2c15-4741-9b55-c1c15be2408c)

![android_x86_emulador_detectado](https://github.com/user-attachments/assets/0636c3a4-a021-4464-8663-d31eeb26d15c)

LDPlayer // Tela de login e pós tentativa de Login

![LDPlayer_tela_login](https://github.com/user-attachments/assets/607fe7ba-e022-48c2-b239-f631e33168d4)

![LDPlayer_emulador_detectado](https://github.com/user-attachments/assets/48784764-bf17-4672-80f7-93b537758504)

MEmu // Tela de login e pós tentativa de login 

![MEmu_tela_login](https://github.com/user-attachments/assets/eff2fcdd-bffa-4cad-b4a3-06ea7795fdce)

![MEmu_emulador_detectado](https://github.com/user-attachments/assets/e3f4ff1d-638c-40e1-910b-933a60489062)

MuMu // Tela de login e pós tentativa de login

![MuMu_tela_login](https://github.com/user-attachments/assets/482552f7-4cc3-47c6-831a-f6c1c049e800)

![MuMu_emulador_detectado](https://github.com/user-attachments/assets/8aabedef-b679-41dd-966f-4b8d75a25614)

RESULTADOS DEVICES FISICOS

Samsung Galaxy S23 Ultra // Tela de login e pós tentativa de login

![S23ULTRA_LOGIN](https://github.com/user-attachments/assets/e2a93ec6-3c53-4339-8631-005d9532a9e8)

![S23ULTRA_POSLOGIN](https://github.com/user-attachments/assets/b59a4ae4-bcda-403e-87cb-a59f57a5f27f)

Xiaomi MI 8 // Tela de login e pós tentativa de login

![XIAOMIMI8_LOGIN](https://github.com/user-attachments/assets/e69b6f65-91f8-4288-b774-ddd632b0e18c)

![XIAOMIMI8_POSLOGIN](https://github.com/user-attachments/assets/b2a18a33-d358-450c-bcab-a684634dbccf)

Motorola Edge 20 // Tela de login e pós tentativa de login

![MOTOROLAEDGE20_LOGIN](https://github.com/user-attachments/assets/8ed3f5a4-f618-417f-bb4c-3de9f8173e97)

![MOTOROLAEDGE20_POSLOGIN](https://github.com/user-attachments/assets/4d7a700b-38da-4ecf-a049-a4c0bde9edf8)

Xiaomi Redmi 12s // Tela de login e pós tentativa de login

![XIAOMIREDMI12S_LOGIN](https://github.com/user-attachments/assets/ba3d6a91-7f08-43be-9789-6e97639acfad)

![XIAOMI_REDMI12S_POSLOGIN](https://github.com/user-attachments/assets/0a4e9ebb-cb75-4c63-a1a7-3aa472ea32f1)

Samsung Galaxy M52  // Tela de login e pós tentativa de login

![SAMSUNGGALAXYM52_LOGIN](https://github.com/user-attachments/assets/0d8dd4c2-b313-4ee7-b671-28f0d3cad0a5)

![SAMSUNGGALAXYM52_POSLOGIN](https://github.com/user-attachments/assets/ff3b46bf-40ca-44c1-9041-44bf09e2f37e)

POSSUI EMULADOR DE PSP INSTALADO, POR ISSO O RESULTADO FALSO POSITIVO 
