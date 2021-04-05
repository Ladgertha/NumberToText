# NumberToText
[![](https://jitpack.io/v/Ladgertha/NumberToText.svg)](https://jitpack.io/#Ladgertha/NumberToText)

Библиотека, которая преобразовывает число в формате Int или Long в строку.

## Пример использования:
```
NumberConverter().convertNumberToString(Int.MIN_VALUE)
```
Результат:
минус два миллиарда сто сорок семь миллионов четыреста восемьдесят три тысячи шестьсот сорок восемь

Подключение:
- Gradle:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
implementation 'com.github.Ladgertha:NumberToText:1.0.0'
```

- Maven:
```
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
</repositories>
```
```
<dependency>
	  <groupId>com.github.Ladgertha</groupId>
	  <artifactId>NumberToText</artifactId>
	  <version>1.0.0</version>
</dependency>
  ```
