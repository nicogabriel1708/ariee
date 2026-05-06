# ARIEE — Arithmetic Expression Evaluator

A Java library and CLI tool for evaluating arithmetic expressions.

---

## ARIEE Core

The core library provides the
[`ArithmeticExpression`](ariee-core/src/main/java/com/nicogabriel/ariee/core/ArithmeticExpression.java) class, which can
evaluate arithmetic expressions given as strings. It supports basic operations as well as parentheses for grouping.

---

### Installation

#### Maven Dependency

To use the library in your project, add the following Maven dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>com.nicogabriel</groupId>
    <artifactId>ariee-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### JAR File

Alternatively, you can download the JAR file from the [releases page](https://github.com/nicogabriel1708/ariee/releases)
or the [Maven Central Repository](https://mvnrepository.com/artifact/com.nicogabriel/ariee-core).

---

### Example

```java
import com.nicogabriel.ariee.core.ArithmeticExpression;

void main() {
    ArithmeticExpression expression = ArithmeticExpression.fromString("5 + 3 * 2");
    double result = expression.evaluate();
}
```

---

## ARIEE CLI

The command line application serves as a convenience tool for evaluating arithmetic expressions directly from the
terminal.

---

### Installation

#### Windows

```bash
winget install ariee
```

#### Linux

```bash
sudo apt install ariee
```

#### macOS

```bash
brew install ariee
```

---

### Usage

Start the interactive REPL:

```bash
ariee
```

Evaluate a single expression (use quotes!):

```bash
ariee "5 + 3 * 2"
```

Read and evaluate an expression from a file:

```bash
ariee -f filename.txt
```
