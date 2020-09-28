# Monoalphabetic-Cipher
- Monoalphabetic cipher and decoder JAVA program for Computer Security and Cryptography project  
- 2020-2 정보보호론 Monoalphabetic 암호화 프로젝트

## Monoalphabetic substitution cipher
  
| A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| S | D | R | T | U | Q | E | T | O | W | C | F | V | X | G | B | H | N | Y | I | A | M | Z | J | L | K |

- 키(Key)를 이용한 암호화  
  - 하나의 알파벳을 다른 알파벳으로 1:1 치환한다.
  - 26!의 경우의 수를 가진다.

- 취약점
  - 알파벳의 통계학적 특성 중 frequency를 이용하여 복호화가 가능하다.

## 암호화 및 복호화 프로그램
### 주요 기능
1. key 생성  

2. 암호화  

3. 복호화  

### 상세 기능
- key 생성 기능을 선택하면, 임의의 key를 생성하여 파일에 저장한다. 

- 암호화 기능을 선택하면, key 파일과 원문 텍스트 파일을 읽어서, 암호문 텍스트 파일을 생성한다. 

- 복호화 기능을 선택하면, key 파일과 암호문 텍스트 파일을 읽어서, 원문 텍스트 파일을 생성한다. 

- 암호와 복호의 대상은 텍스트 파일에서 알파벳만 치환한다. 

## 암호문 공격 프로그램
### 주요 기능
1. 암호문의 알파벳의 빈도수 출력  

2. key와 원문 예측  

### 상세 기능
- 알파벳 빈도수 기반으로 적합한 key를 추측하여, 그에 해당하는 원문을 출력한다. 

- 예측한 key와 원문을 각각 파일로 저장한다. (예: key1.txt, plaintext1.txt)

- 가장 적합할 것으로 예측하는 key와 원문을 1개 이상 생성한다. 
