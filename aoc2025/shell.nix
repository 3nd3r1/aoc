{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = with pkgs; [
    jdk
    python311
  ];

  shellHook = ''
    echo "Java version: $(java -version 2>&1 | head -n1)"
    echo "Python version: $(python3 --version)"
  '';
}
