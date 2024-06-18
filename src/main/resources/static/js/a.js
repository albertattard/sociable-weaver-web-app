function openDocument(){
  window.location.href = `/document/${encodePath(document.getElementById('path').value)}`;
}

function encodePath(path){
  return bytesToBase64(new TextEncoder().encode(path));
}

function decodePath(base64){
  return new TextDecoder().decode(base64ToBytes(base64));
}

function base64ToBytes(base64) {
  const binString = atob(base64);
  return Uint8Array.from(binString, (m) => m.codePointAt(0));
}

function bytesToBase64(bytes) {
  const binString = Array.from(bytes, (byte) =>
    String.fromCodePoint(byte),
  ).join("");
  return btoa(binString);
}
