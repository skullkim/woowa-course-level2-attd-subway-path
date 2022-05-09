package wooteco.subway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Section 은")
public class SectionTest {

    @DisplayName("다른 Section 인스턴스와 역을 비교해서 어느 종류의 역이 같은지를 판단해야 한다.")
    @ParameterizedTest(name = "{index} {displayName} upStation={0} downStation={1} expectedMatchingResult={2}")
    @MethodSource("provideStationAndMatchingResult")
    void matching_Station(final Section newSection, final MatchingResult expectedMatchingResult) {
        final Section section = SectionFactory.from("1a2b3");
        assertThat(section.match(newSection)).isEqualTo(expectedMatchingResult);
    }

    private static Stream<Arguments> provideStationAndMatchingResult() {
        return Stream.of(
                Arguments.of(SectionFactory.from("1a3c3"), MatchingResult.ADD_TO_RIGHT),
                Arguments.of(SectionFactory.from("3c2b3"), MatchingResult.ADD_TO_LEFT),
                Arguments.of(SectionFactory.from("1a2b3"), MatchingResult.SAME_SECTION),
                Arguments.of(SectionFactory.from("3c4d3"), MatchingResult.NO_MATCHED)
        );
    }

    @DisplayName("다른 Section 인스턴스에 비해 거리가 더 작은지를 비교해야 한다.")
    @ParameterizedTest(name = "{index} {displayName} newStation={0} distance={1} expectedResult={2}")
    @MethodSource("provideStationAndDistanceCompareResult")
    void compare_Distance(final Section newSection, final int distance, final boolean expectedResult) {
        final Section section = new Section(StationFactory.from("a"), StationFactory.from("b"), distance);
        assertThat(section.isDistanceLongerThan(newSection)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideStationAndDistanceCompareResult() {
        return Stream.of(
                Arguments.of(SectionFactory.from("1a3c3"), 4, true),
                Arguments.of(SectionFactory.from("1a3c3"), 3, false)
        );
    }

}
