package ay2122s1_cs2103t_w16_2.btbb.logic.parser;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;

/**
 * Functional interface for parser functions.
 *
 * @param <S> Type of output after applying the parser function.
 */
@FunctionalInterface
public interface ParserFunction<S> {
    /**
     * Applies a parser function.
     *
     * @param input Input to parse.
     * @return Output after parsing.
     * @throws ParseException if {@code input} does not conform the expected format.
     */
    S apply(String input) throws ParseException;
}
