*** Settings ***
Documentation     Navigationsflödet för att boka en bil
Library           SeleniumLibrary
Suite Setup       Open Browser    ${BASE_URL}    headlesschrome
Suite Teardown    Close All Browsers

*** Variables ***
${BASE_URL}        https://rental2.infotiv.net/webpage/html/gui/index.php
${USERNAME}        hmire007@gmail.com
${PASSWORD}        hyrbil
${SPEED}           0




*** Test Cases ***

Logga in på befintligt konto
    [Documentation]     Kunden öppnar webbplatsen och loggar in på sitt befintliga konto
    [Tags]              inlogning
    Set Selenium Speed    ${SPEED}
    Maximize Browser Window
    Input Text           id=email       ${USERNAME}
    Input Text           id=password    ${PASSWORD}
    Click Button         id=login
    Page Should Contain Element    css=#welcomePhrase    You are signed in as Hassan
    Close Browser

Ange datum för bokning
    [Documentation]    Kunden anger datum för att boka en bil
    [Tags]              Datumval
     Open Browser    ${BASE_URL}   headlesschrome
    Logga in    ${USERNAME}    ${PASSWORD}

    Input Text    id=start    2024-04-28
    Input Text    id=end    2024-04-29
    Click Button     id=continue
    Page Should Contain Element    css=#questionText    When do you want to make your trip?
    Close Browser

Välj en bil för bokning
    [Documentation]    Kunden väljer en bil för att slutföra bokningen
    [Tags]            bilval
     Open Browser    ${BASE_URL}   headlesschrome
    Logga in    ${USERNAME}   ${PASSWORD}

    Ange datum     2024-04-28   2024-04-29
    Wait Until Element Is Visible    css=.carRow
    ${row}=  Get WebElement  xpath=//tr[contains(@class, "carRow")]
    Click Button  xpath=//input[@id="bookQ7pass5"]
    Page Should Contain Element    css=#questionText    What would you like to drive?
    Close Browser

Slutför bokning med betalningsinformation
    [Documentation]    Kunden fyller i betalningsinformationen för att slutföra bokningen
    [Tags]        betalkort inmatning
    Open Browser    ${BASE_URL}   headlesschrome
    Logga in    ${USERNAME}    ${PASSWORD}

    Ange datum  2024-04-28   2024-04-29
    Välj bil    //tr[contains(@class, "carRow")]   //input[@id="bookQ7pass5"]

    Input Text    css=#cardNum    1234567890123456
    Input Text    css=#fullName    Hassan Mire
    Select From List By Label    css=.mediumInputFields[title="Month"]  10
    Select From List By Label    css=.mediumInputFields[title="Year"]   2024
    Input Text    css=#cvc    123
    Click Button    css=#confirm
     Page Should Contain Element    css=#questionTextSmall  Confirm booking
     Close Browser



Avboka bil
    [Documentation]    Kunden avbokar en bil
    [Tags]            avbokning

    Open Browser    ${BASE_URL}    headlesschrome
    Logga in    ${USERNAME}    ${PASSWORD}
    Ange datum    2024-04-28   2024-04-29
    Välj bil    //tr[contains(@class, "carRow")]   //input[@id="bookQ7pass5"]


     Click Button    id=mypage
    Wait Until Element Is Visible    css=#unBook1
    Click Button    css=#unBook1
    Handle Alert    accept
    Close Browser

Logga ut från kontot
    [Documentation]    Kunden slutför bokningen och loggar ut från kontot
    [Tags]            utlogning

     Open Browser    ${BASE_URL}   headlesschrome
    Logga in    ${USERNAME}    ${PASSWORD}

    Click Button    id=logout
    Page Should Contain Element    css=#createUser
    Close Browser


Negativt testfall: Ogiltiga inloggningsuppgifter
    [Documentation]    Testar inloggning med ogiltiga uppgifter
    [Tags]            Negativt_test
    Open Browser    ${BASE_URL}    headlesschrome
    Input Text    id=email    ogiltig@hassan.com
    Input Text    id=password    355336
    Click Button    id=login
     Page Should Contain Element    css=#signInError    Wrong e-mail or password
    Close Browser

Negativt testfall: Ogiltiga bokningsdatum
    [Documentation]    Testar att försöka ange ogiltiga datum för bokning
    [Tags]             Negativt_test
    Open Browser       ${BASE_URL}    headlesschrome
    Input Text         id=start    2024-03-29
    Input Text         id=end    2024-03-28
    Click Button       id=continue
    Page Should Not Contain Element    css=span.error-message
    Close Browser


*** Keywords ***
Logga in
    [Arguments]    ${username}    ${password}
    Input Text    id=email       ${username}
    Input Password   id=password    ${password}
    Click Button    id=login

Ange datum
    [Arguments]    ${start_date}    ${end_date}
    Input Text    id=start    ${start_date}
    Input Text    id=end    ${end_date}
    Click Button    id=continue

Välj bil
    [Arguments]    ${row_xpath}    ${book_button_xpath}
    ${row}=    Get WebElement    xpath=${row_xpath}
    Click Button    xpath=${book_button_xpath}

Slutför bokning
    [Arguments]    ${card_number}    ${full_name}    ${month}    ${year}    ${cvc}
    Input Text    css=#cardNum    ${card_number}
    Input Text    css=#fullName    ${full_name}
    Select From List By Label    css=.mediumInputFields[title="Month"]  ${month}
    Select From List By Label    css=.mediumInputFields[title="Year"]   ${year}
    Input Text    css=#cvc    ${cvc}
    Click Button    css=#confirm
