package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.AppParameters;
import seedu.address.MainApp;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;

/**
 * Exports records to a text file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports to text file in the \"data\" folder \n"
            + "Parameters: FILENAME\n"
            + "Example: " + COMMAND_WORD + " records1.json";

    public static final String MESSAGE_SUCCESS = "Exported the records!";

    private final File file;

    public ExportCommand(File file) {
        this.file = file;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        readFile(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    public void readFile(Model model) {
        //AddressBookStorage addressBookStorage = new JsonAddressBookStorage(file.toPath());

        //StorageManager storage = new StorageManager(addressBookStorage, null);

        StorageManager storage = new StorageManager(null, null);

        final Logger logger = LogsCenter.getLogger(MainApp.class);

        //Optional<ReadOnlyAddressBook> addressBookOptional;

        try {
            storage.saveAddressBook(model.getAddressBook(), file.toPath());
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
        }
    }
}