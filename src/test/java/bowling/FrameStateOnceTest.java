package bowling;

import bowling.domain.Pinfall;
import bowling.domain.PointSymbol;
import bowling.domain.PointSymbols;
import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class FrameStateOnceTest {
    @Test
    void When_New_Then_Created() {
        assertThat(new FrameStateOnce(new Pinfall(1))).isEqualTo(new FrameStateOnce(new Pinfall(1)));
    }

    @Test
    void Given_Spare_When_Roll_Then_StateSpare() {
        FrameState state = new FrameStateOnce(new Pinfall(1));
        assertThat(state.roll(new Pinfall(9))).isInstanceOf(FrameStateSpare.class);
    }

    @Test
    @DisplayName("스페어 처리가 되었을 때, 스페어 상태의 PointSymbol이 정상인지 테스트")
    void Given_Spare_When_Roll_Then_StateSpareFirstPointSymbolIsSameAsFirstSymbol() {
        FrameState stateOnce = new FrameStateOnce(new Pinfall(1));
        FrameState stateSpare = stateOnce.roll(new Pinfall(9));

        assertThat(stateSpare.pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.ONE, PointSymbol.SPARE));

    }

    @Test
    void Given_Open_When_Roll_Then_StateOpen() {
        FrameState state = new FrameStateOnce(new Pinfall(1));
        assertThat(state.roll(new Pinfall(2))).isInstanceOf(FrameStateOpen.class);
    }

    @Test
    void When_isRollable_Then_False() {
        FrameState state = new FrameStateOnce(new Pinfall(1));
        assertThat(state.isRollable()).isTrue();
    }

    @Test
    void When_Symbol_Then_RightSymbol() {
        FrameState state = new FrameStateOnce(new Pinfall(1));
        assertThat(state.pointSymbols().symbols()).contains(PointSymbol.ONE);
    }
}
