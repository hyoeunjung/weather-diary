package zerobase.weather.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import zerobase.weather.domain.DateWeather;
import zerobase.weather.domain.Diary;
import zerobase.weather.repository.DateWeatherRepository;
import zerobase.weather.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiaryServiceTest {

    @InjectMocks
    private DiaryService diaryService;

    @Mock
    private DiaryRepository diaryRepository;

    @Mock
    private DateWeatherRepository dateWeatherRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("C - 일기를 생성하면 DB에 저장된다")
    void testCreateDiary() {
        LocalDate date = LocalDate.of(2025, 8, 5);
        String text = "날씨 좋아~";

        DateWeather fakeWeather = new DateWeather();
        fakeWeather.setDate(date);
        fakeWeather.setWeather("Clear");
        fakeWeather.setIcon("01d");
        fakeWeather.setTemperature(28.5);

        when(dateWeatherRepository.findAllByDate(date)).thenReturn(Collections.singletonList(fakeWeather));

        diaryService.createDiary(date, text);

        verify(diaryRepository, times(1)).save(argThat(diary ->
                diary.getText().equals(text) &&
                        diary.getWeather().equals("Clear") &&
                        diary.getTemperature() == 28.5 &&
                        diary.getIcon().equals("01d") &&
                        diary.getDate().equals(date)
        ));
    }

    @Test
    @DisplayName("R - 특정 날짜의 일기를 읽어온다")
    void testReadDiary() {
        LocalDate date = LocalDate.of(2025, 8, 5);
        Diary diary = new Diary();
        diary.setDate(date);
        diary.setText("읽기 테스트");

        when(diaryRepository.findAllByDate(date)).thenReturn(List.of(diary));

        List<Diary> result = diaryService.readDiary(date);

        assertEquals(1, result.size());
        assertEquals("읽기 테스트", result.get(0).getText());
    }

    @Test
    @DisplayName("R - 날짜 범위의 일기들을 읽어온다")
    void testReadDiaries() {
        LocalDate start = LocalDate.of(2025, 8, 1);
        LocalDate end = LocalDate.of(2025, 8, 5);
        Diary diary = new Diary();
        diary.setDate(start);
        diary.setText("범위 테스트");

        when(diaryRepository.findAllByDateBetween(start, end)).thenReturn(List.of(diary));

        List<Diary> result = diaryService.readDiaries(start, end);

        assertEquals(1, result.size());
        assertEquals("범위 테스트", result.get(0).getText());
    }

    @Test
    @DisplayName("U - 일기 내용을 수정할 수 있다")
    void testUpdateDiary() {
        LocalDate date = LocalDate.of(2025, 8, 5);
        Diary diary = new Diary();
        diary.setDate(date);
        diary.setText("원래 내용");

        when(diaryRepository.getFirstByDate(date)).thenReturn(diary);

        diaryService.updateDiary(date, "수정된 내용");

        verify(diaryRepository, times(1)).save(argThat(d ->
                d.getText().equals("수정된 내용")
        ));
    }

    @Test
    @DisplayName("D - 특정 날짜의 일기를 삭제할 수 있다")
    void testDeleteDiary() {
        LocalDate date = LocalDate.of(2025, 8, 5);

        doNothing().when(diaryRepository).deleteAllByDate(date);

        diaryService.deleteDiary(date);

        verify(diaryRepository, times(1)).deleteAllByDate(date);
    }
}
