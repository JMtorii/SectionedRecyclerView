package com.juntorii.testapplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SectionedRecyclerAdapterTests {

    /**
     * Header 0
     * --- Row 0
     * --- Row 1
     * --- Row 2
     *
     * Header 1
     * --- Row 0
     *
     * Header 2
     *
     * Header 3
     * --- Row 0
     * --- Row 1
     * --- Row 2
     * --- Row 3
     * --- Row 4
     * --- Row 5
     *
     * Header 4
     */

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    SectionedRecyclerAdapter adapter;

    @Before
    public void setup() {
        when(adapter.numberOfSections()).thenReturn(5);
        when(adapter.numberOfRowsInSection(0)).thenReturn(3);
        when(adapter.numberOfRowsInSection(1)).thenReturn(1);
        when(adapter.numberOfRowsInSection(2)).thenReturn(0);
        when(adapter.numberOfRowsInSection(3)).thenReturn(6);
        when(adapter.numberOfRowsInSection(4)).thenReturn(0);
    }

    @Test
    public void positionFromIndexPath_withSection0RowNegative1_shouldReturn0() throws Exception {
        int position = adapter.positionFromIndexPath(new IndexPath(0, IndexPath.INVALID));
        assertEquals(0, position);
    }

    @Test
    public void positionFromIndexPath_withSection0Row0_shouldReturn1() throws Exception {
        int position = adapter.positionFromIndexPath(new IndexPath(0, 0));
        assertEquals(1, position);
    }

    @Test
    public void positionFromIndexPath_withSection1RowNegative1_shouldReturn4() throws Exception {
        int position = adapter.positionFromIndexPath(new IndexPath(1, IndexPath.INVALID));
        assertEquals(4, position);
    }

    @Test
    public void positionFromIndexPath_withSection2RowNegative1_shouldReturn6() throws Exception {
        int position = adapter.positionFromIndexPath(new IndexPath(2, IndexPath.INVALID));
        assertEquals(6, position);
    }

    @Test
    public void positionFromIndexPath_withSection3Row5_shouldReturn13() throws Exception {
        int position = adapter.positionFromIndexPath(new IndexPath(3, 5));
        assertEquals(13, position);
    }

    @Test
    public void positionFromIndexPath_withSection4RowNegative1_shouldReturn14() throws Exception {
        int position = adapter.positionFromIndexPath(new IndexPath(4, -1));
        assertEquals(14, position);
    }


    @Test
    public void indexPathFromPosition_withPosition0_shouldReturnSection0RowNegative1() {
        IndexPath indexPath = adapter.indexPathFromPosition(0);
        assertEquals(0, indexPath.section);
        assertEquals(IndexPath.INVALID, indexPath.row);
    }

    @Test
    public void indexPathFromPosition_withPosition1_shouldReturnSection0Row0() {
        IndexPath indexPath = adapter.indexPathFromPosition(1);
        assertEquals(0, indexPath.section);
        assertEquals(0, indexPath.row);
    }

    @Test
    public void indexPathFromPosition_withPosition4_shouldReturnSection1RowNegative1() {
        IndexPath indexPath = adapter.indexPathFromPosition(4);
        assertEquals(1, indexPath.section);
        assertEquals(IndexPath.INVALID, indexPath.row);
    }

    @Test
    public void indexPathFromPosition_withPosition6_shouldReturnSection2RowNegative1() {
        IndexPath indexPath = adapter.indexPathFromPosition(6);
        assertEquals(2, indexPath.section);
        assertEquals(IndexPath.INVALID, indexPath.row);
    }

    @Test
    public void indexPathFromPosition_withPosition13_shouldReturnSection3Row5() {
        IndexPath indexPath = adapter.indexPathFromPosition(13);
        assertEquals(3, indexPath.section);
        assertEquals(5, indexPath.row);
    }

    @Test
    public void indexPathFromPosition_withPosition14_shouldReturnSection4RowNegative1() {
        IndexPath indexPath = adapter.indexPathFromPosition(14);
        assertEquals(4, indexPath.section);
        assertEquals(IndexPath.INVALID, indexPath.row);
    }
}