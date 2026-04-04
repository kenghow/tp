package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEMERIT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.address.logic.commands.DemeritCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new DemeritCommand object.
 */
public class DemeritCommandParser implements Parser<DemeritCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DemeritCommand
     * and returns a DemeritCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DemeritCommand parse(String args) throws ParseException {
        ParserUtil.checkForUnknownPrefixes(args, DemeritCommand.MESSAGE_USAGE, PREFIX_STUDENT_ID,
                PREFIX_DEMERIT_INDEX, PREFIX_REMARK);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_DEMERIT_INDEX, PREFIX_REMARK);

        if (!argMultimap.arePrefixesPresent(PREFIX_STUDENT_ID, PREFIX_DEMERIT_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(DemeritCommand.MESSAGE_USAGE);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT_ID, PREFIX_DEMERIT_INDEX, PREFIX_REMARK);

        StudentId studentId = ParserUtil.parseStudentId(
                argMultimap.getValue(PREFIX_STUDENT_ID).get());
        int ruleIndex = ParserUtil.parsePositiveInt(
                argMultimap.getValue(PREFIX_DEMERIT_INDEX).get());
        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("");

        return new DemeritCommand(studentId, ruleIndex, remark);
    }
}
