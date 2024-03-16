function fn(){ 
  function read(file) {
    try {
      return karate.read(file);
    } catch (e) {
      karate.log("File not found: " + file)
      return {};
    }
  }
  
  karate.set(read('classpath:config.yml'));
}