[![Published on Vaadin  Directory](https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg)](https://vaadin.com/directory/component/maskedtextfield-for-vaadin-8)
[![Stars on Vaadin Directory](https://img.shields.io/vaadin-directory/star/maskedtextfield-for-vaadin-8.svg)](https://vaadin.com/directory/component/maskedtextfield-for-vaadin-8)

# MaskedTextField for Vaadin 8

Vaadin 8 version of [MaskedTextField](http://vaadin.com/addon/maskedtextfield)


# Usage
Overview of currently available masks:
```
    # - any digit
    U - upper-case letter
    L - lower-case letter
    ? - any letter
    A - any number or character
    * - anything
    H - hex sign (0-9, a-f or A-F)
    ' - Escape character, used to escape any of the special formatting characters.
    ~ - +/- sign
```
Any character not matching one of the above mask character or if it escaped with the single quote character (') is considered to be a literal.

Some mask examples:
```
    Phone Number: (###) ###-####
    USPS Express Mail: EU#########'US
    Date / time: ##/##/#### ##:##
    State: UU
    HTML Color: '#HHHHHH
    An capitalized 6 letter word: ULLLLL
```

# Known issues:

- [Issues](https://github.com/andersonfreitas/vaadin-masked-textfield/issues)

