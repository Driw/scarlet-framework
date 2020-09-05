# Scarlet Core

Core library for any other project of scarlet-framework.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You need have [Maven](https://maven.apache.org/) to install Scarlet Core and you can found it on [Maven Repository](https://mvnrepository.com/).

### Installing

Specify the maven import as the following example on your [pom.xml](https://maven.apache.org/pom.html) inside of your project.
Remember the project need be at your project.

```
<dependency>
    <groupId>org.diverproject</groupId>
    <artifactId>scarlet-core</artifactId>
    <version>0.1.0</version>
</dependency>
```

1. The library has some default behaviors that you can change using Scarlet singleton:

```java
Scarlet scarlet = Scarlet.getInstance();
scarlet.setSomething(...);
```

2. You can read language messages automatically using **@LanguageAutoloader** annotation in yours Language enums:

```java
String filepath = "language/en-us.ini";
File folder = new File(filepath);
int affected = LanguageLoader.autoLoad(folder));
```

```java
public enum ExampleLanguage implements Language {

	FIRST_MESSAGE("First Message"),
	SECOND_MESSAGE("Second Message");

	private String format;

	private ExampleLanguage(String format) {
		this.setFormat(format);
	}

	@Override
	public int getCode() {
		return this.ordinal();
	}

	@Override
	public String getFormat() {
		return this.format;
	}

	@Override
	public void setFormat(String format) {
		this.format = format;
	}
}
```

To language messages works, it's necessary a pattern specification of messages and implementation [Language Implementation](Language.md).

## Running the tests

The test can be executed just running the project with JUnit Test 5.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/diverproject/diamond-lang/tags).

*The revision log have somethings different because it's used developers found more easy changes made and make github commit messages more **clean***.

## Authors

* **Andrew Mello da Silva** - *Developer* - [Driw](https://github.com/Driw)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* **Billie Thompson** - *[Readme template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)* - [PurpleBooth](https://github.com/PurpleBooth)
