---
layout: page
title: User Guide
---

SalesPunch is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you can type fast, SalesPunch can get your contact
management tasks done faster than traditional GUI apps.

Salespeople managing client contacts who prefer a CLI

- has a need to manage a significant number of contacts
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition**: Users that want to log their sales funnel cycle and keep track of their leads. You can do it
faster on a CLI with better NLP. Helps salesperson keep track of all the necessary details and set reminders/alerts,
prioritise sales tasks

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `salespunch.jar` from [here](https://github.com/AY2223S2-CS2103-W16-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your SalesPunch.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar salespunch.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
    <!-- TODO: Need to update the picture here -->

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all contacts.

   - `add n/Charlotte Oliveiro g/female p/93210283 e/charlotte@example.com c/Mac King l/America o/entrepreneur j/CEO a/Blk 11 Ang Mo Kio Street 74, #11-04` : Adds a contact named `Charlotte Oliveiro` to the Address Book.

   - `delete 3` : Deletes the 3rd contact shown in the current list.

   - `clear` : Deletes all contacts.

   - `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the address book.

Format: `add [n/NAME] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [c/COMPANY] [l/LOCATION] [o/OCCUPATION] [j/JOBTITLE] [a/ADDRESS] [t/TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact must include all fields except tag.
</div>

Examples:

- `add n/Charlotte Oliveiro g/female p/93210283 e/charlotte@example.com c/Mac King l/America o/entrepreneur j/CEO a/Blk 11 Ang Mo Kio Street 74, #11-04`
- `add n/Amy Bee g/female p/85355255 e/amy@gmail.com c/Tesleh l/Singapore o/engineer j/industrial engineer a/123, Jurong West Ave 6, #08-111 t/friend`

### Sorting all persons : `sort`

Sorts all persons in the address book based on an attribute.

Format: `sort [name] [gender] [phone number] [email] [company] [location] [occupation] [job title] [address] [remark] [status]`

- At least one of the optional fields must be provided.
- The address book will be sorted based on the specified attribute by their value.

Examples:

- `sort name` Sorts all persons alphabetically by name.
- `sort occupation` Sorts all persons alphabetically by occupation.

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [c/COMPANY] [l/LOCATION] [o/OCCUPATION] [j/JOBTITLE] [a/ADDRESS] [t/TAG] …​`

- Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
- You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Assign lead status: `status`

Adds one or more statuses to a contact. Statuses allow you to search by tags.
Tags must be a single word with no whitespaces.

If no status is specified, lists the status associated with the contact.

The lead status follows a set of rules, to be implemented in v1.3

Format:  
`status INDEX_NUMBER|NAME`
`status INDEX_NUMBER|NAME [STATUS …]`

Examples:
`status 1` or `status David` Returns the status of ID `1` or the status of `David`
`status David s/Q` Assigns the status of `David` to be `Qualified`

### Finding a contact name: `find`

Search for a contact based on a keyword, or by specifying its index number.

Format: `find [KEYWORD(S)]`

- The find is case-insensitive. e.g. `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is found.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find Dewy ` - returns `Dewy Thompson` or `Majorie Dewy`

### Finding a contact tag: `findtag`

Search for a contact based on their tags.

Format: `findtag [<valid tag>]`

- The search tag must be enclosed in [brackets]. e.g `[friends]` will match `[Friends]` and will searching `friends` will not match `[Friends]`
- The search is case-insensitive. e.g `[friends]` will match `[Friends]`
- Only full tags will be matched e.g. `friend` will not match `friends`
- Persons matching at least one tag will be returned (i.e. `OR` search).
  e.g. `findtag [friends]` will return `Hans Gruber`, `Bo Yang` if they are associated with having a [friends] tag

Examples:

- `findtag [friends]` - returns all valid contacts that are associated with tag being searched like [friends], `Dewy Thompson`

### Finding a contact tag: `findlead`

Search for a contact based on their leads.

Format: `findlead <valid lead status>`

The user can use either the long form or short form method to search

The 4 stages of lead status
UNCONTACTED: `Uncontacted` or `U`
WORKING: `Working` or `W` 
QUALIFIED: `Qualified` or `Q`
UNQUALIFIED: `Unqualified` or `X`

- The search is case-insensitive. e.g `u` will match `U`
- The search works for both short form and long form. e.g `U` will match `Uncontacted`
- Persons matching the tag will be returned 
  e.g. `findlead U` will return `Hans Gruber` who is a contact with a lead status of `Uncontacted`

Examples:

- `findlead <leadstatus>` - returns all contacts with the associated lead status, `Dewy Thompson` or `Majorie Dewy`

### Finding a contact tag: `findall`

Search for a contact based on all attributes of a Person recorded in the addressbook.

Format: `findall <any keyword that matches any attribute>`

The user can search for any attribute and if it matches with any Person, that person will be listed.

- The search is case-insensitive. e.g `u` will match `U`
- The search will match with any attribute
- Persons matching the search will be returned 
  e.g. `findall Blk 30` will return contacts with address attributes of `Blk 16 Hello Drive` who is a contact with a lead status of `Blk Goodbye Drive`

Examples:

- `findall Blk` - returns all contacts with the `blk` in any of their attributes, `Dewy Thompson` or `Majorie Dewy`

### Finding a contact tag: `findtxn`

Search for a single contact and all txns related to this contact. The user must enter an existing user that is in the database and must match exactly the name in the database.

Format: `findtxn <exact match with person name>`

- The search is case-insensitive. e.g `[John Doe]` will match `[john doe]`
- The spaces before and after keywords does not matter. e.g. `[ Hans Bo  ]` will match `[Bo Hans]`
  e.g. `findtxn [John Doe]` will return `John Doe` and all txns with the same owner name `John Doe`

Examples:

- `findtxn [John Doe]` - returns the contact with valid and associated txns, `John Doe`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

- Deletes the person at the specified `INDEX`.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete 2` deletes the 2nd person in the address book.
- `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding a transaction: `addtxn`

Adds a transaction record to the address book.

Format: `addtxn [td/DESCRIPTION] [tv/VALUE] [ts/STATUS] [to/OWNER]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
All field must be provided, and transaction status should be either 'open' or 'closed'.
</div>

Examples:

- `addtxn td/Sample Transaction tv/100 ts/open to/John Doe`

### Listing all transaction records : `listtxn`

Shows a list of all transaction records in the sales book.

Format: `listtxn`

### Editing a transaction record : `edittxn`

Edits an existing transaction record in the sales book.

Format: `edittxn INDEX [td/DESCRIPTION] [tv/VALUE] [ts/STATUS] [to/OWNER]`

- Edits the transaction record at the specified `INDEX`.
- The index refers to the index number shown in the displayed transaction list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.

Examples:

- `edittxn 1 ts/closed` Edits the transaction status of the 1st transaction record to be 'closed'.

### Deleting a transaction record : `deletetxn`

Deletes the specified transaction record from the sales book.

Format: `deletetxn INDEX`

- Deletes the transaction record at the specified `INDEX`.
- The index refers to the index number shown in the displayed transaction list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `listtxn` followed by `deletetxn 2` deletes the 2nd transaction record in the sales book.

### Adding a task: `addtask`

Adds a task to the specific person in the address book.

Format: `addtask INDEX [at/TASK_DESCRIPTION]`

* Adds a task to the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* The task added to the person will have the description `TASK_DESCRIPTION`

### Clearing all tasks: `cleartask` 

Clears all tasks to the specific person in the address book.

Format: `cleartask INDEX`

* Clears all tasks to the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​


### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits and closes the program

Format: `exit`

### Saving the data

SalesPunch contact data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

<!-- need to update the json -->

SalesPunch data are saved as a JSON file `[JAR file location]/data/updatethis____.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

---

## Command summary

_italic_ - optional

| Action         | Format, Examples                                                                                                                                                                                                                                                                           |
| -------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | --- |
| **Add**        | `add [n/NAME] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [c/COMPANY] [l/LOCATION] [o/OCCUPATION] [j/JOBTITLE] [a/ADDRESS] [t/TAG] ...​` <br> e.g., `add n/Amy Bee g/female p/85355255 e/amy@gmail.com c/Tesleh l/Singapore o/engineer j/industrial engineer a/123, Jurong West Ave 6, #08-111` |
| **Sort**       | `sort [name] [gender] [phone number] [email] [company] [industry] [occupation] [job title] [address] [remark] [status]` <br> e.g., `sort name`                                                                                                                                             |
| **Add Txn**    | `addtxn [td/DESCRIPTION] [tv/VALUE] [ts/STATUS] [to/OWNER] ` <br> e.g., `addtxn [td/DESCRIPTION] [tv/VALUE] [ts/STATUS] [to/OWNER]`                                                                                                                                                        |
| **Clear**      | `clear`                                                                                                                                                                                                                                                                                    |
| **Delete**     | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                                        |     |
| **Delete Txn** | `deletetxn INDEX`<br> e.g., `deletetxn 3`                                                                                                                                                                                                                                                  |
| **Edit**       | `edit INDEX [n/NAME] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [c/COMPANY] [i/INDUSTRY] [o/OCCUPATION] [j/JOBTITLE] [a/ADDRESS] [t/TAG] …​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                                                               |
| **Edit Txn**   | `edittxn INDEX [td/DESCRIPTION] [tv/VALUE] [ts/STATUS] [to/OWNER]` <br> e.g., `edittxn 1 ts/closed`                                                                                                                                                                                        |
| **Status**     | `status INDEX_NUMBER` _`[STATUS …]`_<br> `status NAME` _`[STATUS …]`_<br> e.g., `status 1, status David closed-won`                                                                                                                                                                        |
| **List**       | `list`                                                                                                                                                                                                                                                                                     |
| **List Txn**   | `listtxn`                                                                                                                                                                                                                                                                                  |
| **Help**       | `help`                                                                                                                                                                                                                                                                                     |
