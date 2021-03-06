package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("times tests") {
    assert(times(List('a', 'b', 'a')) === List(('a', 2), ('b', 1)))
  } 
  
  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  test("test for French code") {
    assert(decodedSecret === List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("convert to Code Table - t1") {
    new TestTrees {
      assert(convert(t1) === List(('a', List(0)), ('b', List(1))))
    }
  }  
  
  test("convert to Code Table - t2") {
    new TestTrees {
      assert(convert(t2) === List(('a', List(0, 0)), ('b', List(0, 1)), ('d', List(1))))
    }
  }   
  
  test("decode and encode a very short text should be identity - Code Table") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

  
  test("create tree, decode and encode a text") {
    new TestTrees {
      val list = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.".toList
      val tree = createCodeTree(list)
      
      assert(!(encode(tree)(list).length == 0), "Encoded bit string should non empty")
      assert(decode(tree, encode(tree)(list)) === list)
      assert(decode(tree, quickEncode(tree)(list)) === list)
    }
  }
}
